package mcm.edu.ph.liston_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView heroName,monsterName,heroHP,monsterHP,heroMP,monsterMP,heroDPS,monsterDPS,combattxt;
    Button buttonNextTurn;
    ImageButton skill1,skill2,skill3;

    //Hero Status
    String HeroName = "Warrior";
    int HeroHp = 1500;
    int HeroMp = 1000;
    int HeroMinDamage = 50;
    int HeroMaxDamage = 100;
    //Monster Status
    String MonsterName = "Cyclops";
    int MonsterHp = 3000;
    int MonsterMp = 600;
    int MonsterMinDamage = 40;
    int MonsterMaxDamage = 60;


    //Game Turn
    int turnNumber = 1;
    boolean disabledstatus = false;
    int statuscounter = 0;
    int buttoncounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//this line hides the action bar
        setContentView(R.layout.activity_main);
        // XML Ids for Text and Buttons


        heroName = findViewById(R.id.heroName);
        monsterName = findViewById(R.id.monsterName);
        heroHP = findViewById(R.id.heroHP);
        monsterHP = findViewById(R.id.monsterHP);
        heroMP = findViewById(R.id.heroMP);
        monsterMP = findViewById(R.id.monsterMP);
        buttonNextTurn = findViewById(R.id.buttonNextTurn);
        heroDPS = findViewById(R.id.heroDPS);
        monsterDPS = findViewById(R.id.monsterDPS);
        combattxt = findViewById(R.id.combattxt);


          heroName.setText(HeroName);
          heroHP.setText(String.valueOf(HeroHp));
          heroMP.setText(String.valueOf(HeroMp));

        monsterName.setText(MonsterName);
        monsterHP.setText(String.valueOf(MonsterHp));
        monsterMP.setText(String.valueOf(MonsterMp));

        heroDPS.setText(String.valueOf(HeroMinDamage)+" ~ "+ String.valueOf(HeroMaxDamage));
        monsterDPS.setText(String.valueOf(MonsterMinDamage)+" ~ "+ String.valueOf(MonsterMaxDamage));

        skill1 = findViewById(R.id.btnskill1);
        skill2 = findViewById(R.id.btnskill2);
        skill3 = findViewById(R.id.btnskill3);

        //button onClickListener
        buttonNextTurn.setOnClickListener(this);
        skill1.setOnClickListener(this);
        skill2.setOnClickListener(this);
        skill3.setOnClickListener(this);

    }

    @Override
    public void onClick (View v) {


        combattxt.findViewById(R.id.combattxt);

        Random randomizer = new Random();
        int heroDPS = randomizer.nextInt(HeroMaxDamage - HeroMinDamage) + HeroMinDamage ;
        int monsterDPS = randomizer.nextInt(MonsterMaxDamage - MonsterMinDamage) + MonsterMinDamage ;

        if(turnNumber % 2 != 1){//if it is enemy's turn, disable button
            skill1.setEnabled(false);
        }
        else if(turnNumber%2 == 1){
            skill1.setEnabled(true);
        }

        if(buttoncounter>0){
            skill1.setEnabled(false);
            // buttoncounter--;
        }
        else if(buttoncounter==0){
            skill1.setEnabled(true);
        }

        switch(v.getId()) {
            case R.id.btnskill1:

                MonsterHp = MonsterHp - 200;
                turnNumber++;
                monsterHP.setText(String.valueOf(MonsterHp));
                buttonNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");

                combattxt.setText("Our Warrior "+String.valueOf(heroName) +" used stun punch!. It dealt "+String.valueOf(200) + " damage to the enemy. The enemy is stunned for 3 turns");

                disabledstatus = true;
                statuscounter = 3;

                if(MonsterHp < 0){ //even
                    combattxt.setText("Our Warrior "+String.valueOf(heroName) +" dealt "+String.valueOf(heroDPS) + " damage to the enemy. The Warrior is victorious!");
                    HeroHp = 1500;
                    MonsterHp = 3000;
                    turnNumber= 1;
                    buttonNextTurn.setText("Reset Game");
                }
                buttoncounter=12;
                break;
            case R.id.btnskill2:
                if(turnNumber % 2 == 1) {
                    HeroHp = HeroHp + 200;
                    turnNumber--;
                    heroHP.setText(String.valueOf(HeroHp));
                    buttonNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                    combattxt.setText("Our Warrior " + String.valueOf(heroName) + " used heal touch!. You are Healed in " + String.valueOf(200) + " HP ");


                    if (MonsterHp < 0) { //even
                        combattxt.setText("Our Warrior " + String.valueOf(heroName) + " dealt " + String.valueOf(heroDPS) + " damage to the enemy. The Warrior is victorious!");
                        HeroHp = 1500;
                        MonsterHp = 3000;
                        turnNumber = 1;
                        buttonNextTurn.setText("Reset Game");
                    }
                    buttoncounter = 0;
                }
                break;
            case R.id.btnskill3:
                if(turnNumber % 2 == 1) {
                    MonsterHp = MonsterHp - 150;
                    turnNumber--;
                    monsterHP.setText(String.valueOf(MonsterHp));
                    buttonNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                    combattxt.setText("Our Warrior " + String.valueOf(heroName) + " used steal dagger!. It dealt " + String.valueOf(150) + " damage to the enemy. You stole the enemy's items");


                    if (MonsterHp < 0) { //even
                        combattxt.setText("Our Warrior " + String.valueOf(heroName) + " dealt " + String.valueOf(heroDPS) + " damage to the enemy. The Warrior is victorious!");
                        HeroHp = 1500;
                        MonsterHp = 3000;
                        turnNumber = 1;
                        buttonNextTurn.setText("Reset Game");
                    }
                    buttoncounter = 0;

                }


                break;
            case R.id.buttonNextTurn:
                //

                if(turnNumber % 2 == 1){ //odd
                    MonsterHp = MonsterHp - heroDPS;
                    turnNumber++;
                    monsterHP.setText(String.valueOf(MonsterHp));
                    buttonNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");

                    combattxt.setText("Our Warrior "+String.valueOf(heroName) +" dealt "+String.valueOf(heroDPS) + " damage to the enemy.");

                    if(MonsterHp < 0){ //even
                        combattxt.setText("Our Warrior "+String.valueOf(heroName) +" dealt "+String.valueOf(heroDPS) + " damage to the enemy. The Warrior is victorious!");
                        HeroHp = 1500;
                        MonsterHp = 3000;
                        turnNumber= 1;
                        buttoncounter=0;
                        buttonNextTurn.setText("Reset Game");
                    }
                    buttoncounter--;
                }
                else if(turnNumber%2 != 1){ //even

                    if(disabledstatus==true){
                        combattxt.setText("The enemy is still stunned for "+String.valueOf(statuscounter)+ "turns");
                        statuscounter--;
                        turnNumber++;
                        buttonNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");
                        if(statuscounter==0){
                            disabledstatus=false;
                        }
                    }
                    else{
                        HeroHp = HeroHp - monsterDPS;
                        turnNumber++;
                        heroHP.setText(String.valueOf(HeroHp));
                        buttonNextTurn.setText("Next Turn ("+ String.valueOf(turnNumber)+")");

                        combattxt.setText("The monster "+String.valueOf(MonsterName)+" dealt "+String.valueOf(monsterDPS)+ " damage to the enemy.");

                        if(HeroHp < 0){
                            combattxt.setText("The monster "+String.valueOf(MonsterName)+" dealt "+String.valueOf(monsterDPS)+ " damage to the enemy. Game Over");
                            HeroHp = 1500;
                            MonsterHp = 3000;
                            turnNumber= 1;
                            buttoncounter=0;
                            buttonNextTurn.setText("Reset Game");
                        }
                    }
                    // buttoncounter--;
                }
                break;

                }


        }
    }
