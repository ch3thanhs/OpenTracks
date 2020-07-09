package de.dennisguse.opentracks.io.file.importer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.dennisguse.opentracks.util.IntentUtils;

public class DirectoryChooserActivity extends AppCompatActivity {

    private static final int DIRECTORY_PICKER_REQUEST_CODE = 6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivityForResult(intent, DIRECTORY_PICKER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == DIRECTORY_PICKER_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri directoryUri = resultData.getData();

                Intent intent = IntentUtils.newIntent(this, ImportActivity.class);
                intent.putExtra(ImportActivity.EXTRA_DIRECTORY_URI_KEY, directoryUri);
                startActivity(intent);
            }
            finish();
        }
    }
}