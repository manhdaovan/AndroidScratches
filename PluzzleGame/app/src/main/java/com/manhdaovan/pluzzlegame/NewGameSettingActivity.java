package com.manhdaovan.pluzzlegame;

import android.graphics.Color;
import android.os.Bundle;

// uCrop

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.manhdaovan.pluzzlegame.utils.Constants;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.util.Locale;
import java.util.Random;


public class NewGameSettingActivity extends ImageCroppingBase {

    private static final String TAG = "SampleActivity";

    // Start here
    private NumberPicker npRowPieces;
    private NumberPicker npColumnPieces;
    private RadioGroup rdGridSize;
    private ImageView selectedImg;

    private Uri croppedImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_setting);

        setupUI();
    }

    @SuppressWarnings("ConstantConditions")
    private void setupUI() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.new_game_setting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        npRowPieces = (NumberPicker) findViewById(R.id.np_row);
        npRowPieces.setDisplayedValues(Constants.ROW_NUM_PIECES);
        npRowPieces.setMinValue(Constants.ROW_OFFSET);
        npRowPieces.setMaxValue(Constants.ROW_OFFSET + Constants.ROW_NUM_PIECES.length - 1);

        npColumnPieces = (NumberPicker) findViewById(R.id.np_column);
        npColumnPieces.setDisplayedValues(Constants.COLUMN_NUM_PIECES);
        npColumnPieces.setMinValue(Constants.COLUMN_OFFSET);
        npColumnPieces.setMaxValue(Constants.COLUMN_OFFSET + Constants.COLUMN_NUM_PIECES.length - 1);

        rdGridSize = (RadioGroup) findViewById(R.id.radio_grid_size);
        rdGridSize.check(R.id.setting_grid_size_random);
        rdGridSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.setting_grid_size_select:
                        setDisplayNumberPickers(View.VISIBLE);
                        break;
                    default:
                        setDisplayNumberPickers(View.GONE);
                        break;
                }
            }
        });

        selectedImg = (ImageView) findViewById(R.id.imgView_choose_img);

        findViewById(R.id.btn_pick_from_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });

        findViewById(R.id.btn_pick_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickRandom();
            }
        });
    }

    private void setDisplayNumberPickers(int mode) {
        LinearLayout piecesSelectSection = (LinearLayout) findViewById(R.id.pieces_select_section);
        piecesSelectSection.setVisibility(mode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_game_setting_ok:
                if (croppedImgUri == null) {
                    Toast.makeText(NewGameSettingActivity.this, "No selected picture", Toast.LENGTH_LONG).show();
                } else {
                    Intent gameSetting = new Intent(NewGameSettingActivity.this, GamePlayActivity.class);
                    gameSetting.setData(croppedImgUri);
                    gameSetting.putExtra(Constants.INTENT_ROW_PIECES, npRowPieces.getValue());
                    gameSetting.putExtra(Constants.INTENT_COLUMN_PIECES, npColumnPieces.getValue());
                    startActivity(gameSetting);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "requestCode: " + requestCode);
        Log.e(TAG, "resultCode: " + resultCode);
        Log.e(TAG, "data: " + data);

        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_SELECT_PICTURE) {
                final Uri selectedUri = data.getData();
                Log.e(TAG, "selectedUri: " + selectedUri);
                if (selectedUri != null) {
                    Log.e(TAG, "selectedUri != null");
                    startCropActivity(data.getData());
                } else {
                    Log.e(TAG, "selectedUri == null");
                    Toast.makeText(NewGameSettingActivity.this, R.string.toast_cannot_retrieve_selected_image, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                Log.e(TAG, "handleCropResult");
                handleCropResult(data);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Log.e(TAG, "handleCropError");
            handleCropError(data);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_STORAGE_READ_ACCESS_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickFromGallery();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void pickFromGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.permission_read_storage_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.label_select_picture)), Constants.REQUEST_SELECT_PICTURE);
        }
    }

    private void pickRandom() {
        Random random = new Random();
        int minSizePixels = 800;
        int maxSizePixels = 2400;
        startCropActivity(Uri.parse(String.format(Locale.getDefault(), "https://unsplash.it/%d/%d/?random",
                minSizePixels + random.nextInt(maxSizePixels - minSizePixels),
                minSizePixels + random.nextInt(maxSizePixels - minSizePixels))));
    }

    private void startCropActivity(@NonNull Uri uri) {
        String fileName = "ABCDEF";
        String destinationFileName = fileName + ".jpg";

        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));

        uCrop = basisConfig(uCrop);
        uCrop = advancedConfig(uCrop);

        uCrop.start(NewGameSettingActivity.this);
    }

    /**
     * In most cases you need only to set crop aspect ration and max size for resulting image.
     *
     * @param uCrop - ucrop builder instance
     * @return - ucrop builder instance
     */
    private UCrop basisConfig(@NonNull UCrop uCrop) {
        return uCrop;
    }

    /**
     * Sometimes you want to adjust more options, it's done via {@link com.yalantis.ucrop.UCrop.Options} class.
     *
     * @param uCrop - ucrop builder instance
     * @return - ucrop builder instance
     */
    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();
        Log.e(TAG, options.toString());

        options.setCompressionFormat(Constants.DEFAULT_IMG_FORMAT);
        options.setCompressionQuality(Constants.DEFAULT_COMPRESS_QUALITY);

        options.setFreeStyleCropEnabled(true);

        options.setCropGridColumnCount(npColumnPieces.getValue());
        options.setCropGridRowCount(npRowPieces.getValue());

        /*
        If you want to configure how gestures work for all UCropActivity tabs

        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        * */

        /*
        This sets max size for bitmap that will be decoded from source Uri.
        More size - more memory allocation, default implementation uses screen diagonal.

        options.setMaxBitmapSize(640);
        * */


       /*

        Tune everything (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧

        options.setMaxScaleMultiplier(5);
        options.setImageToCropBoundsAnimDuration(666);
        options.setDimmedLayerColor(Color.CYAN);
        options.setCircleDimmedLayer(true);
        options.setShowCropFrame(false);
        options.setCropGridStrokeWidth(20);
        options.setCropGridColor(Color.GREEN);
        options.setCropGridColumnCount(2);
        options.setCropGridRowCount(1);
        options.setToolbarCropDrawable(R.drawable.your_crop_icon);
        options.setToolbarCancelDrawable(R.drawable.your_cancel_icon);

        // Color palette
        options.setToolbarColor(ContextCompat.getColor(this, R.color.your_color_res));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.your_color_res));
        options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.your_color_res));
        options.setToolbarWidgetColor(ContextCompat.getColor(this, R.color.your_color_res));
        options.setRootViewBackgroundColor(ContextCompat.getColor(this, R.color.your_color_res));

        // Aspect ratio options
        options.setAspectRatioOptions(1,
            new AspectRatio("WOW", 1, 2),
            new AspectRatio("MUCH", 3, 4),
            new AspectRatio("RATIO", CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO),
            new AspectRatio("SO", 16, 9),
            new AspectRatio("ASPECT", 1, 1));

       */

        return uCrop.withOptions(options);
    }

    private void handleCropResult(@NonNull Intent result) {
        croppedImgUri = UCrop.getOutput(result);
        if (croppedImgUri != null) {
            Toast.makeText(getApplicationContext(), "VKL handleCropResult", Toast.LENGTH_LONG).show();
            selectedImg.setImageDrawable(null); // Force redraw ImageView
            selectedImg.setImageURI(croppedImgUri);
        } else {
            Toast.makeText(NewGameSettingActivity.this, R.string.toast_cannot_retrieve_cropped_image, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void handleCropError(@NonNull Intent result) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Log.e(TAG, "handleCropError: ", cropError);
            Toast.makeText(NewGameSettingActivity.this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(NewGameSettingActivity.this, R.string.toast_unexpected_error, Toast.LENGTH_SHORT).show();
        }
    }
}
