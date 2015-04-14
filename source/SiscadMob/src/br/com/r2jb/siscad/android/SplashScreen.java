/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 *
 * @author Joaquim
 */
public class SplashScreen extends Activity implements Runnable {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler h = new Handler();
		h.postDelayed(this, 3000);//aqui é definido o delay do handler em milisegundos


	}

	public void run(){
		startActivity(new Intent(this, SiscadMob.class));//aqui é iniciada nossa 2 activity
		finish();//aqui é chamado o método finish pra finalizar a activity atual no caso SplashScreen
	}

}