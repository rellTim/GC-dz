public class JvmComprehension { // Подгрузка класса в Class Loading
    //Существует 3 этапа загрузки Class ( a) Application ClassLoader, b) Platform ClassLoader, c) Bootstrap ClassLoader)
    //Этап подгрузски происходит следующим образом a -> b -> c и снизу вверх начинается подрузка классов если на верхнем
    //уровне класс не нашелся ClassLoader спускается на этап ниже и так до Application ClassLoader, если этот ClassLoader
    //не найдет искомый класс прозайдет исключение (ClassNotFoundException), но если какой-то ClassLoader найдет искомые
    // класс он их загрузит (Может быть 4-ый ClassLoader (пользовательский)).

    //После подгрузки искомого класса при помощи ClassLoader последует процесс связывания (Linking)
    //(Linking (Verify, Prepare, Resolve). После связывания происходить Initialization
    //при инициализации загружаются статические поля, методы, блоки.

    public static void main(String[] args) {
        int i = 1;                      // 1 в стеке вызывается метод main где переменной "i" присваивается значение 1
        Object o = new Object();        // 2 в стеке уже в вызванном методе main ранее создается ссылка "o" на объект new Object()
        Integer ii = 2;                 // 3 в стеке уже в вызванном методе main ранее создается ссылка "ii" на переменную Integer и присваивается значение 2
        printAll(o, i, ii);             // 4 закрывает стек с методом main отрывается новый стек по вверх старого printAll
        // внутри этого стека создается ссылка на объект Object с ссылкой o и объект Integer с ссылкой на него ii в сетке
        System.out.println("finished"); // 7 открывается стек println туда передается значение finished закрывается стек GC удаляет мусор
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5 в открытом стеке метода printAll создается ссылка на объект в куче Integer uselessVar с значением 700
        System.out.println(o.toString() + i + ii);  // 6 закрывается предыдущий стек открывается новый стек с методом println передается туда ссылки на o + i + ii после выделяется место в куче String закрывается стек printAll открывается стек toString выполнятеся с преедачей ссылки o, закрывается стек
    }
}