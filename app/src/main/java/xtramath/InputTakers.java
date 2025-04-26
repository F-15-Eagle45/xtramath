package main.java.xtramath;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class InputTakers {
    static class AddInputTaker extends InputTaker {
        AddInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            for (int i = 0; i < num.length; i++) {
                num[i] = random.nextInt(max - min) + min;
            }
            for (int i = 0; i < num.length - 1; i++) {
                System.out.print(num[i] + " + ");
            }
            System.out.print(num[num.length - 1] + " = ");

        }

        @Override
        protected boolean checkAnswer() {
            int sum = 0;
            for (int i = 0; i < num.length; i++) {
                sum += num[i];
            }
            return sum == answer;
        }

    }

    static class SubInputTaker extends NonComInputTaker {
        SubInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            pseudoMin = min;
            pseudoMax = max - terms;
            for (int i = (num.length - 1); i >= 0; i--) {
                num[i] = random.nextInt(pseudoMax - pseudoMin) + pseudoMin;
                pseudoMin = num[i];
                pseudoMax++;
            }
            for (int i = 0; i < num.length - 1; i++) {
                System.out.print(num[i] + " - ");
            }
            System.out.print(num[num.length - 1] + " = ");
            pseudoMin = min;
            pseudoMax = max;
        }

        @Override
        protected boolean checkAnswer() {
            int sum = num[0];
            for (int i = 1; i < num.length; i++) {
                sum -= num[i];
            }
            return sum == answer;
        }
    }

    static class MultInputTaker extends InputTaker {
        MultInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            for (int i = 0; i < num.length; i++) {
                num[i] = random.nextInt(max - min) + min;
            }
            for (int i = 0; i < num.length - 1; i++) {
                System.out.print(num[i] + " x ");
            }
            System.out.print(num[num.length - 1] + " = ");
        }

        @Override
        protected boolean checkAnswer() {
            int sum = 1;
            for (int i = 0; i < num.length; i++) {
                sum *= num[i];
            }
            return sum == answer;
        }
    }

    static class TabmInputTaker extends InputTaker {
        TabmInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            num[0] = random.nextInt(max - min) + min;
            num[1] = random.nextInt(10 - 1) + 1;
            System.out.print(num[0] + " x " + num[1] + " = ");
        }

        @Override
        protected boolean checkAnswer() {
            return (num[0] * num[1]) == answer;
        }
    }

    static class DivInputTaker extends NonComInputTaker {
        DivInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            pseudoMin = min;
            pseudoMax = max / terms;
            num[num.length] = random.nextInt(pseudoMax - pseudoMin) + pseudoMin;
            for (int i = (num.length - 2); i >= 0; i--) {
                num[i] = (random.nextInt(pseudoMax - pseudoMin) + pseudoMin) * num[i - 1];
            }
        }

        @Override
        protected boolean checkAnswer() {
            int sum = num[0];
            for (int i = 1; i < num.length; i++) {
                sum /= num[i];
            }
            return sum == answer;
        }
    }

    static class TabdInputTaker extends InputTaker {
        TabdInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            num[1] = random.nextInt(10 - 1) + 1;
            num[0] = (num[1] * random.nextInt(max - min) + min);
            System.out.print(num[0] + " / " + num[1] + " = ");
        }

        @Override
        protected boolean checkAnswer() {
            return (num[0] / num[1]) == answer;
        }
    }

    static class sqrInputTaker extends InputTaker {
        sqrInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            num[0] = random.nextInt(max - min) + min;
            for (int i = 0; i < num.length - 1; i++) {
                System.out.print(num[i] + " ^ 2");
            }
            System.out.print(num[num.length - 1] + " = ");
        }

        @Override
        protected boolean checkAnswer() {
            return (num[0] * num[0]) == answer;
        }
    }

    static class SqrtInputTaker extends InputTaker {
        SqrtInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            num[0] = random.nextInt(((int) Math.sqrt(max)) - min) + min;
            num[0] *= num[0];
            System.out.print("sqrt(" + num[0] + ") = ");
        }

        @Override
        protected boolean checkAnswer() {
            return (num[0] == (answer * answer));
        }
    }

    static class CubeInputTaker extends InputTaker {
        CubeInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            num[0] = random.nextInt(max - min) + min;
            System.out.print(num[0] + " ^ 3 = ");
        }

        @Override
        protected boolean checkAnswer() {
            return (num[0] * num[0] * num[0]) == answer;
        }
    }

    static class CbrtInputTaker extends InputTaker {
        CbrtInputTaker(int min, int max, int terms) {
            super(min, max, terms);
        }

        @Override
        protected void askQuestion() {
            num[0] = random.nextInt(((int) Math.cbrt(max)) - min) + min;
            num[0] *= num[0] * num[0];
            System.out.print("cbrt(" + num[0] + ") = ");
        }

        @Override
        protected boolean checkAnswer() {
            return num[0] == (answer * answer * answer);
        }
    }
}
