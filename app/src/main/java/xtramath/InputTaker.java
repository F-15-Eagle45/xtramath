package main.java.xtramath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class InputTaker implements Runnable {
    protected int num[];
    protected int answer;
    protected int min;
    protected int max;
    protected int terms;
    protected BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public int questions;
    public int correct;
    public List<Integer> wrong = new ArrayList<Integer>();
    public boolean running = true;
    protected Random random = new Random();
    Thread t;

    public InputTaker(int min, int max, int terms) {
        t = new Thread(this, "Input Taker Thread");
        t.start();

        this.min = min;
        this.max = max;
        this.terms = terms;

        num = new int[terms];
    }

    abstract protected void askQuestion();

    // common run for all input taking
    public void run() {
        while (running) {
            askQuestion();
            try {
                while (!br.ready() && running) {
                    Thread.sleep(200);
                }
                if (running) {
                    answer = Integer.parseInt(br.readLine());
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            if (checkAnswer()) {
                correct++;
            } else {
                wrong.add((Integer) questions);
            }
            questions++;
        }
    }

    abstract protected boolean checkAnswer();
}
