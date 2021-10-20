package com.example.oceandeep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.oceandeep.MESSAGE";
    Button generateContentButton, oceanParkButton, showSigButton;
    ImageButton settingsButton;
    SharedPreferences defaultPref;
    static int totalAllContent = 11;
    static int[] generatedAllID = new int[0];
    static int totalChildContent = 5;
    static int[] generatedChildID = new int[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTheme(R.style.MainScreenAppTheme);

        setContentView(R.layout.activity_main);

        settingsButton = (ImageButton) findViewById(R.id.settingsButton);
        generateContentButton = (Button) findViewById(R.id.generateContentButton);
        oceanParkButton = (Button) findViewById(R.id.oceanParkButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsButton();
            }
        });
        generateContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChildFriendly()) {
                    generateChildContent();
                }
                else {
                    generateAllContent();
                }
            }
        });
        oceanParkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //palitan to next
            }
        });
    }

    public boolean isChildFriendly() {
        boolean isChildFriendly;
        defaultPref = PreferenceManager.getDefaultSharedPreferences(this);
        isChildFriendly = defaultPref.getBoolean("child_option", false);
        return isChildFriendly;
    }

    public void settingsButton() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void generateAllContent() {
        Intent intent = new Intent(this, C1_Adult.class);
        //int contentID = Integer.parseInt(randomAllID());
        int contentID = Integer.parseInt(addToGeneratedAllID());

        if (contentID == 1) {
            intent = new Intent(this, C1_Adult.class);
        }
        else if (contentID == 2) {
            intent = new Intent(this, C2_Adult.class);
        }
        else if (contentID == 3) {
            intent = new Intent(this, C3_Adult.class);
        }
        else if (contentID == 4) {
            intent = new Intent(this, C4_Child.class);
        }
        else if (contentID == 5) {
            intent = new Intent(this, C5_Child.class);
        }
        else if (contentID == 6) {
            intent = new Intent(this, C6_Adult.class);
        }
        else if (contentID == 7) {
            intent = new Intent(this, C7_Adult.class);
        }
        else if (contentID == 8) {
            intent = new Intent(this, C8_Adult.class);
        }
        else if (contentID == 9) {
            intent = new Intent(this, C9_Child.class);
        }
        else if (contentID == 10) {
            intent = new Intent(this, C10_Child.class);
        }
        else if (contentID == 11) {
            intent = new Intent(this, C11_Child.class);
        }

        //intent.putExtra(EXTRA_MESSAGE, randomID());
        startActivity(intent);
    }

    public void generateChildContent() {
        Intent intent = new Intent(this, C1_Adult.class);
        int contentID = Integer.parseInt(addToGeneratedChildID());

        if (contentID == 1) {
            intent = new Intent(this, C4_Child.class);
        }
        else if (contentID == 2) {
            intent = new Intent(this, C5_Child.class);
        }
        else if (contentID == 3) {
            intent = new Intent(this, C9_Child.class);
        }
        else if (contentID == 4) {
            intent = new Intent(this, C10_Child.class);
        }
        else if (contentID == 5) {
            intent = new Intent(this, C11_Child.class);
        }

        //intent.putExtra(EXTRA_MESSAGE, randomID());
        startActivity(intent);
    }

    public static String randomAllID() {
        String randomID = "" + (int)((Math.random()* 11)+1);
        return randomID;
    }

    public static String randomChildID() {
        String randomID = "" + (int)((Math.random()* 5)+1);
        return randomID;
    }

    public static boolean checkIfGeneratedAdult(int idToCheck) {
        for (int x: generatedAllID) {
            if (idToCheck == x ) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfGeneratedChild(int idToCheck) {
        for (int x: generatedChildID) {
            if (idToCheck == x ) {
                return true;
            }
        }
        return false;
    }

    //main method for generating id. always generates a unique id.
    public static String addToGeneratedAllID () {
        int temporary = Integer.parseInt(randomAllID());

        int[] temporaryArray = new int[generatedAllID.length+1];
        for (int x=0; x<generatedAllID.length; x++) {
            temporaryArray[x] = generatedAllID[x];
        }
        generatedAllID = temporaryArray;
        while(checkIfGeneratedAdult(temporary)) {
            temporary = Integer.parseInt(randomAllID());

            //reset generated ids if already full ###check mo nga bakit pag dito lang to nakalagay saka gumagana
            if (generatedAllID.length > totalAllContent) {
                generatedAllID = new int[1];
            }
        }
        generatedAllID[generatedAllID.length-1] = temporary;
        return "" + temporary;
    }

    //main method for generating id. always generates a unique id.
    public static String addToGeneratedChildID() {
        int temporary = Integer.parseInt(randomChildID());

        int[] temporaryArray = new int[generatedChildID.length+1];
        for (int x=0; x<generatedChildID.length; x++) {
            temporaryArray[x] = generatedChildID[x];
        }
        generatedChildID = temporaryArray;
        while(checkIfGeneratedChild(temporary)) {
            temporary = Integer.parseInt(randomChildID());

            //reset generated ids if already full ###check mo nga bakit pag dito lang to nakalagay saka gumagana
            if (generatedChildID.length > totalChildContent) {
                generatedChildID = new int[1];
            }
        }
        generatedChildID[generatedChildID.length-1] = temporary;
        return "" + temporary;
    }
}
