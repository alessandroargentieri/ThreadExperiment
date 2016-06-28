package mawashi.alex.threadexperiment;

import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;
    TextView Counter1;
    TextView Counter2;
    TextView Counter3;
    ThreadUno nuovoThreadUno;
    Thread nuovoThreadDue;
    AsyncTre nuovoThreadTre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Counter1 = (TextView) findViewById(R.id.textView2);
        Counter2 = (TextView) findViewById(R.id.textView3);
        Counter3 = (TextView) findViewById(R.id.textView4);
    }

    public void Method1(View v){
        counter1 = 0;
        try {
            if (nuovoThreadUno.isAlive()) {
                nuovoThreadUno.interrupt();
                nuovoThreadUno = null;
            }
        }catch(Exception e){Log.d("ThreadUno","ThreadUno appena istanziato");}
        nuovoThreadUno = new ThreadUno();
        nuovoThreadUno.start();

    }

    public void Method2(View v){
        counter2 = 0;
        try {
            if (nuovoThreadDue.isAlive()) {
                nuovoThreadDue.interrupt();
                nuovoThreadDue = null;
            }
        }catch(Exception e){Log.d("ThreadDue","ThreadDue appena istanziato");}
        ThreadDue T2 = new ThreadDue();
        nuovoThreadDue = new Thread(T2);
        nuovoThreadDue.start();
    }

    public void Method3(View v){
        counter3 = 0;
        try {
            if (!nuovoThreadTre.isCancelled()) {
                nuovoThreadTre.cancel(true);
                nuovoThreadTre = null;
            }
        }catch(Exception e){Log.d("ThreadTre","ThreadTre appena istanziato");}
        nuovoThreadTre = new AsyncTre();
        nuovoThreadTre.execute();

    }



    public class ThreadUno extends Thread{
        public void run(){
            while(true){
                long istante = System.currentTimeMillis();
                while(System.currentTimeMillis()-istante<1000){
                    //DOING NOTHING
                }
                counter1++;
                runOnUiThread(new Runnable(){
                    public void run() {//Interazione con l'interfaccia
                        Counter1.setText("Counter: " + counter1);
                    }
                });
            }
        }
    }


    public class ThreadDue implements Runnable{
        @Override
        public void run() {
            while(true){
                long istante = System.currentTimeMillis();
                while(System.currentTimeMillis()-istante<1000){
                    //DOING NOTHING
                }
                counter2++;
                runOnUiThread(new Runnable(){
                    public void run() {//Interazione con l'interfaccia
                        Counter2.setText("Counter: " + counter2);
                    }
                });
            }
        }
    }


    private class AsyncTre extends AsyncTask<Void,Void,Void> {
        boolean k = true;
        @Override
        protected void onPreExecute(){ }
        @Override
        protected Void doInBackground(Void...params){
            while(k=true){
                long istante = System.currentTimeMillis();
                while(System.currentTimeMillis()-istante<1000){
                    //DOING NOTHING
                }
                counter3++;
                runOnUiThread(new Runnable(){
                    public void run() {//Interazione con l'interfaccia
                        Counter3.setText("Counter: " + counter3);
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){super.onPostExecute(result);}
    }


}
