package fr.epsi.gostyle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GoStyleActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final String FILE_NAME="user-data";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void showCameraBtn(){
        ImageView imageView=findViewById(R.id.imageViewCamera);
        if(imageView!=null) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(this);
        }
    }

    protected void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected void createFile(String result) throws IOException {
        File file = new File(this.getFilesDir(), FILE_NAME);

        try (FileOutputStream fileOutputStream = this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){
            fileOutputStream.write(result.getBytes());
        }
        String fileContent = fileReaderUserData();

        if (!fileContent.isEmpty()){
            CouponListView.display(GoStyleActivity.this);
        }
    }

    protected String fileReaderUserData() throws FileNotFoundException {
        FileInputStream fileInputStream= this.openFileInput(FILE_NAME);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        finally {
            return stringBuilder.toString();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewBack:
                finish();
                break;
            case R.id.imageViewCamera:
                ScanCodeActivity.display(GoStyleActivity.this);
                break;
        }
    }
}
