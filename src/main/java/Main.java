import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Проверка использование одного arrayList в 2 потоках.
     * Наглядно показывается, почему ArrayList непотокобезопасен.
     *
     * Сначала заполняем поток 1000 элементов, для упрощения используются числа int от 1 до 1000.
     * Потом запускаем два потока, где в одном идем с начала и выводим элемент и его индекс, а в другом идем с конца и меняем элемент путем увеличения его на 5.
     * Также если запустить программу несколько раз, то есть вероятность, что ответ всегда будет разным.
     * В результате можно сделать вывод, что при подобном использовании в ArrayList не гарантирована корректная работа, т.е. потоки могут выполняться в произвольном порядке.
     * Поэтому рекомендуется использовать потокбезопасные варианты списка (массива), либо более точно описать работу с данным листом.
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++)
            list.add(i + 1);

        Thread thread1 = new Thread(() -> {
            for (int j = 0; j < list.size(); j++)
                System.out.println("i = " + j + " element = " + list.get(j));
        });

        Thread thread2 = new Thread(() -> {
            for (int j = list.size() - 1; j >= 0; j--)
                list.set(j, j + 5);
        }) ;

        thread1.start();
        thread2.start();

        while (thread1.isAlive() || thread2.isAlive())
            Thread.sleep(1000);

//        for (Integer is : list)
//            System.out.println(is);
    }
}
