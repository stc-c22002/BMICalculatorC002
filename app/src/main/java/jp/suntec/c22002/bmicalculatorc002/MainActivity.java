package jp.suntec.c22002.bmicalculatorc002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button取得p100,104
        Button btCalculator=findViewById(R.id.btCalculator);
        Button btClear=findViewById(R.id.btClear);

        //インスタンスを生成p100
        ClickListener listener=new ClickListener();

        //ボタンにリスナ設定p100,104
        btCalculator.setOnClickListener(listener);
        btClear.setOnClickListener(listener);
    }

    private class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Edittext取得
            EditText inputAge=findViewById(R.id.etAge);
            EditText inputHt=findViewById(R.id.etHt);
            EditText inputWt=findViewById(R.id.etWt);

            //押されたボタン取得
            int id=v.getId();

            if(id==R.id.btCalculator){
                //入力された文字取得
                String  AgeStr=inputAge.getText().toString();
                String  WtStr=inputWt.getText().toString();
                String  HtStr=inputHt.getText().toString();

                int AgeInt= Integer.parseInt(AgeStr);

                float HtFl=Float.parseFloat(HtStr)/100;
                float WtFl=Float.parseFloat(WtStr);

                //textview取得
                TextView BmiA=findViewById(R.id.tvBmiA);
                TextView WtA=findViewById(R.id.tvWtA);
                TextView WtOk=findViewById(R.id.tvWtOk);
                TextView tBmi=findViewById(R.id.tvBmi);
                TextView tkg=findViewById(R.id.tvKg2);

                String a="";
                float bmi;
                float okWt=0;

                //カウプ指数　g/m*m *10 22>=肥りすぎ 19>少し太り気味 15>正常 13>やせ 10>栄養失調 10<=消耗症

                if(AgeInt<16){
                    ConfirmDialogFragment dialogFragment=new ConfirmDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(),"ConfirmDialogFragment");

                    if(AgeInt<6){
                        bmi=WtFl/(HtFl*HtFl);
                        okWt=(17*HtFl*HtFl);

                        if(bmi>=22){
                            a="肥りすぎ";
                        }else if(bmi>19){
                            a="少し太り気味";
                        }else if(bmi>15){
                            a="正常";
                        }else if(bmi>13){
                            a="やせ";
                        }else if(bmi>10){
                            a="栄養失調";
                        }else{
                            a="消耗症";
                        }
                    }else{//ローレル指数 kg/(m*m*m)*10
                        bmi=WtFl/(HtFl*HtFl*HtFl)*10;
                        okWt=13*HtFl*HtFl*HtFl;

                        if(bmi>=160){
                            a="ふとりすぎ";
                        }else if(bmi>=145){
                            a="ふとってる";
                        }else if(bmi>=115){
                            a="ふつう";
                        }else if(bmi>=100){
                            a="やせてる";
                        }else{
                            a="やせすぎ";
                        }

                    }
                }
                else{   //bmi
                    bmi=WtFl/(HtFl*HtFl);
                    okWt=22*HtFl*HtFl;


                    if(bmi<18.5){
                        a="低体重";
                    }else if(bmi<25){
                        a="普通体重";
                    }else if(bmi<30){
                        a="肥満(1度)";
                    }else if(bmi<35){
                        a="肥満(2度)";
                    }else if(bmi<40){
                        a="肥満(3度)";
                    }else{
                        a="肥満(4度)";
                    }
                }

                BmiA.setText(a);
                WtA.setText(String.format("%.1f",okWt));
                WtOk.setText("あなたの適正体重は");
                tBmi.setText("あなたの肥満度は");
                tkg.setText("kg");

            }else if(id==R.id.btClear){
                inputAge.setText("");
                inputWt.setText("");
                inputHt.setText("");
            }
        }
    }
}