package com.example.myapplication;

import android.widget.ImageView;
import android.widget.TextView;

public class Evalutation_nutri {

    public void Evaluation_Lipid(Float quantité, TextView text, ImageView imageView){

        if (quantité < 3.0 ){
            text.setText(quantité+" mg Graisses  en quantité "+"petite");
            imageView.setImageResource(R.drawable.round_green);
        }
        if (quantité <=20 && quantité>=3){
            text.setText(quantité+" mg Graisses s en quantité "+"modérée");
            imageView.setImageResource(R.drawable.round_yellow);
        }else { text.setText(quantité+" mg Graisses en quantité "+"élevée");
                 imageView.setImageResource(R.drawable.round_red); }
    };
    public void Evaluation_Acide_g_s(Float quantité, TextView text, ImageView imageView){

        if (quantité < 1.5 ){
            text.setText(quantité+" mg Graisses saturées en quantité "+"petite");
            imageView.setImageResource(R.drawable.round_green);

        }
        if (quantité <=5 && quantité>=1.5){
            text.setText(quantité+" mg Graisses saturées en quantité "+"modérée");
            imageView.setImageResource(R.drawable.round_yellow);
        }else {  text.setText(quantité+" mg Graisses saturées en quantité "+"élevée");
            imageView.setImageResource(R.drawable.round_red); }
    };

    public void Evaluation_sucres(Float quantité, TextView text, ImageView imageView){

        if (quantité < 5 ){
            text.setText(quantité+" mg Sucre en quantité "+"petite");
            imageView.setImageResource(R.drawable.round_green);
        }
        if (quantité <=12.5 && quantité>=5){
            text.setText(quantité+" mg Sucre en quantité "+"modérée");
            imageView.setImageResource(R.drawable.round_yellow);
        }else {
            text.setText(quantité+" mg Sucre en quantité "+"élevée");
            imageView.setImageResource(R.drawable.round_red);

        };
    }


    public void Evaluation_sel(Float quantité, TextView text, ImageView imageView){

        if (quantité < 0.3 ){

            text.setText(quantité+" mg Sel en quantité "+"petite");
            imageView.setImageResource(R.drawable.round_green);
        }
        if (quantité <=1.5 && quantité>=0.3){
            text.setText(quantité+" mg Sel en quantité "+"modérée");
            imageView.setImageResource(R.drawable.round_yellow);
        }else {
            text.setText(quantité+" mg Sel en quantité "+"élevée");
            imageView.setImageResource(R.drawable.round_red);
        }
    }
}
