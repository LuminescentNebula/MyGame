package com.example.mygame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final static int  CardAmount=3;
    Card[] PreGen={ //Массив стандартных карт
            new Card(1,3,5,0, R.string.headline001,R.string.lore001,R.drawable.card_001_viking),
            new Card(2,2,4,4, R.string.headline002,R.string.lore002,R.drawable.card_002_vampire),
            new Card(3,1,3,2, R.string.headline003,R.string.lore003,R.drawable.card_003_bird),
            new Card(4,2,3,0, R.string.headline004,R.string.lore004,R.drawable.card_004_souleater),
            new Card(5,3,3,1, R.string.headline005,R.string.lore005,R.drawable.card_005_mandrake),
            new Card(6,2,2,0, R.string.headline006,R.string.lore006,R.drawable.card_006_instructor),
            new Card(7,1,4,2, R.string.headline007,R.string.lore007,R.drawable.card_007_ghidorah),
            new Card(8,0,7,0, R.string.headline008,R.string.lore008,R.drawable.card_008_son),
            new Card(9,5,1,0, R.string.headline009,R.string.lore009,R.drawable.card_009_hackerman)
    };

    Card[] PlayerCards = new Card[CardAmount];
    Card[] EnemyCards = new Card[CardAmount];


    private static final String TAGCardSetter = "CardSetter";
    private static final String TAGCardAttack = "CardAttack";
    private static final String TAGSetParam   = "CardParam";


    String[] subtitles = {"Headline","RedGem","BlueGem","Cost","Description","LoreText","Picture"};//Массив названий частей карт

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    //Hiding status bar
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //TODO  NullPointerException
            getSupportActionBar().hide();   //Hiding title bar


        View decorView = getWindow().getDecorView();    //Hiding navigation bar
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        /*if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    //Проверка, что версия ситема достаточна для AutoTextSizeType
        TODO autotextsize;
        }*/
    }

    public void Gen(View view) {
        EditText field = findViewById(R.id.CardNumberGenerate); //Поле в которое вводили номер карты
        Random r = new Random();
        int cardNum = r.nextInt(PreGen.length);  //Случайное число (картинок для карт всего 9)
        Log.i(TAGCardSetter,"field = "  + field.getText().toString());
        Log.i(TAGCardSetter,"random = " + cardNum);
        TextView toFillText;    ///Поле в которе надо будет установить текст
        int num=Integer.parseInt(field.getText().toString());//Число, которе ввели в поле для номера карты
        if (num<=3)
            {
            PlayerCards[num-1]=PreGen[cardNum];
            }
        else
            {
                EnemyCards[num-4]=PreGen[cardNum];
            }
        String baseID  = (num>3 ? "Enemy":"Player") //Название карты
                + "Card"
                + (Integer.parseInt(String.valueOf(field.getText())) > 3 ? Integer.parseInt(String.valueOf(field.getText())) - 3 : Integer.parseInt(String.valueOf(field.getText())))
                + "_";

        // Полное название нужной части карты (PlayerCard1_Headline, EnemyCard3_RedGem и т.д.)
        String ID = baseID + subtitles[0];
        toFillText = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillText.setText(PreGen[cardNum].getHeadline());
        Log.i(TAGCardSetter,"Headline set");

        ID = baseID + subtitles[1];
        toFillText = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillText.setText(String.valueOf(PreGen[cardNum].getHp()));
        Log.i(TAGCardSetter,"Hp set");

        ID = baseID + subtitles[2];
        toFillText = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillText.setText(String.valueOf(PreGen[cardNum].getPower()));
        Log.i(TAGCardSetter,"Power set");

        ID = baseID + subtitles[3];
        toFillText = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillText.setText(PreGen[cardNum].getCost());
        Log.i(TAGCardSetter,"Cost set");

        ID = baseID + subtitles[4];
        toFillText = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillText.setText(getResources().getIdentifier("type"+ PreGen[cardNum].getType(), "string", getPackageName()));
        Log.i(TAGCardSetter,"Type set");

        ID = baseID + subtitles[5];
        toFillText = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillText.setText(PreGen[cardNum].getLore());
        Log.i(TAGCardSetter,"Lore set");

        ID = baseID + subtitles[6];//Установка изображения
        ImageView toFillPicture;
        toFillPicture = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        toFillPicture.setImageDrawable(getDrawable(PreGen[cardNum].getPicture()));
        Log.i(TAGCardSetter,"Picture set");

    }

    public void Fight(View view) {
        EditText firstCard = findViewById(R.id.FirstCard);
        EditText secondCard = findViewById(R.id.SecondCard);

        int num1=Integer.parseInt(firstCard.getText().toString());
        int num2=Integer.parseInt(secondCard.getText().toString());

        PlayerCards[num1-1].decreaseHp(EnemyCards[num2-4].getPower());
        Log.i(TAGCardAttack,"PlayerCard"+ num1 +" hp decreased");
        EnemyCards[num2-4].decreaseHp(PlayerCards[num1-1].getPower());
        Log.i(TAGCardAttack,"EnemyCard"+ num2 +" hp decreased");

        String ID  = "PlayerCard"+ num1;
        ConstraintLayout Card = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        if (PlayerCards[num1-1].getHp()==0){
            Card.setVisibility(View.GONE);
        }else {
          ID=ID+"_RedGem";
          TextView Gem=findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
          Gem.setText(String.valueOf(PlayerCards[num1-1].getHp()));
        }

        ID  = "EnemyCard"+ (num2 - 3);
        Card = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
        if (EnemyCards[num2-4].getHp()==0){
            Card.setVisibility(View.GONE);
        } else {
            ID=ID+"_RedGem";
            TextView Gem=findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
            Gem.setText(String.valueOf(EnemyCards[num2-4].getHp()));
        }
    }

    //Не работает
    public void SetParam(View view) {
        EditText field = findViewById(R.id.Param);
        EditText Cardfield = findViewById(R.id.CardParam);
        RadioButton radiohp = findViewById(R.id.radio_hp);
        RadioButton radiopower = findViewById(R.id.radio_power);
        String ID  = (Integer.parseInt(String.valueOf(Cardfield.getText()))>3 ? "Enemy":"Player")
                + "Card"
                + (Integer.parseInt(String.valueOf(Cardfield.getText())) > 3 ? Integer.parseInt(String.valueOf(Cardfield.getText())) - 3 : Integer.parseInt(String.valueOf(Cardfield.getText())))
                + "_";
        Log.i(TAGSetParam,ID);

        int CardNum=Integer.parseInt(String.valueOf(Cardfield.getText()));
        int param = Integer.parseInt(String.valueOf(field.getText()));
        Log.i(TAGSetParam,"Card = " + CardNum);
        Log.i(TAGSetParam,"Param = " + param);

        if (radiohp.isChecked()){
            Log.i(TAGSetParam,"RedGem");
            ID=ID+"RedGem";
            Log.i(TAGSetParam,"ID = "+ID);
            if (CardNum>3){
                Log.i(TAGSetParam,"Enemy hp set");
                EnemyCards[CardNum-4].setHp(param);
            } else {
                Log.i(TAGSetParam,"Player hp set");
                PlayerCards[CardNum-1].setHp(param);
            }
            TextView Card = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
            Card.setText(String.valueOf((CardNum)>3 ? EnemyCards[CardNum-4].getHp() : PlayerCards[CardNum-1].getHp()) );
        } else {
            if (radiopower.isChecked()){
                Log.i(TAGSetParam,"BlueGem");
                ID=ID+"BlueGem";
                Log.i(TAGSetParam,"ID = "+ID);
                if (CardNum>3){
                    Log.i(TAGSetParam,"Enemy power set");
                    EnemyCards[CardNum-4].setPower(param);
                } else {
                    Log.i(TAGSetParam,"Player power set");
                    PlayerCards[CardNum-1].setPower(param);
                }
                TextView Card = findViewById(getResources().getIdentifier(ID, "id", getPackageName()));
                Card.setText(String.valueOf(CardNum>3 ? EnemyCards[CardNum-4].getPower() : PlayerCards[CardNum-1].getPower()) );
            }
        }
    }

}

