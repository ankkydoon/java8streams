import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class demo {

    public static List<Student> getStudents() {
        Student student1 = new Student("Adam", 2, 3.6, "male", 10, Arrays.asList("swimming", "basketball", "volleyball"));
        Student student2 = new Student("Jenny", 2, 3.8, "female", 11, Arrays.asList("swimming", "gymnastics", "soccer"));
        /**
         * 3rd grade students
         */
        Student student3 = new Student("Emily", 3, 4.0, "female", 12, Arrays.asList("swimming", "gymnastics", "aerobics"));
        Student student4 = new Student("Dave", 3, 4.0, "male", 15, Arrays.asList("swimming", "gymnastics", "soccer"));
        /**
         * 4th grade students
         */
        Student student5 = new Student("Sophia", 4, 3.5, "female", 10, Arrays.asList("swimming", "dancing", "football"));
        Student student6 = new Student("James", 4, 3.9, "male", 22, Arrays.asList("swimming", "basketball", "baseball", "football"));

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6);
        return students;
    }

    public static int calculate(List<Integer> numbers) {
       return numbers
                .stream()
                .reduce((x,y)->x+y).get();
    }

    public static Set<Integer> getKidNames(List<Person> people) {
      return  people.stream()
                .filter(kid ->kid.getAge()< 18)
                .map(list -> list.getAge())
                .collect(Collectors.toSet());
    }

    public static String getString(List<Integer> list) {
       return
               list.stream()
                       .map(x-> x%2==0 ? 'e'+x.toString() : 'o'+ x.toString())
                       .collect(Collectors.joining(","));
    }




    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
    return Arrays.stream(names)
            .filter( name->name.length()>5)
            .map(nam->nam.length())
            .reduce(0,(x,y)->x+y);
    }

    public static Collection<String> mapToUppercase(String... names) {
        return Arrays.stream(names)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static List<String> transform(List<List<String>> collection) {
        return collection.stream()
                .flatMap(collect->collect.stream())
                .collect(Collectors.toList());

    }

//    public static Person getOldestPerson(List<Person> people) {
//        Person oldestPerson = new Person("", 0);
//        for (Person person : people) {
//            if (person.getAge() > oldestPerson.getAge()) {
//                oldestPerson = person;
//            }
//        }
//        return oldestPerson;
//    }

//    public static Person getOldestPerson(List<Person> people) {
//      return people.stream().max(Comparator.comparing(Person::getAge)).get();
//    }


    public static void main(String[] args) {

        System.out.println(getStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGender , Collectors.collectingAndThen(Collectors.counting(),Long::intValue)
                )));
        System.out.println( getStudents().stream()
                .map(Student::getName)
                .collect(Collectors.joining("-&-")));

        System.out.println( getStudents().stream()
                .map(Student::getName)
                .collect(Collectors.counting()));


        Comparator<Student> comparator = (x,y) -> Double.compare(x.getGpa(), y.getGpa());
        System.out.println(
                getStudents().stream()
                        .collect(Collectors.maxBy(Comparator.comparing(Student->Student.getGpa())))
        );

        System.out.println(getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam"));


        System.out.println(getStudents()
                .stream()
                .filter(student -> student.getGpa() > 3.0)
                .map(student -> student.getGradeLevel())
                .reduce(0,Integer::sum));

      List<Long> test=   LongStream.of(1,2,3).boxed().collect(Collectors.toList());


         Stream<Integer> stream = Stream.iterate(1, x -> x*2);

        Stream.generate(new Random()::nextInt).limit(5).forEach(System.out::println);

        stream.mapToInt(Integer::intValue).sum();



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside runnable\n");
            }
        });

        System.out.println(
                getStudents()
                        .stream()
                        .filter(student -> student.getGender().equals("female"))
                        .collect(Collectors.toList()));



       Optional<Double> val= getStudents()
                .stream()
                .map(student -> student.getGpa())
                .reduce((aDouble, aDouble2) -> aDouble*aDouble2);

        System.out.println(val.get().intValue());


        System.out.println(getStudents().stream()
                .reduce((student1, student2) -> (student1.getGpa() > student2.getGpa() ?  student1 : student2)));



        Comparator<Student> comparator1 = (x, y) -> x.getName().compareTo(y.getName());
        System.out.println(
                getStudents().stream()
                        .sorted(Comparator.comparing(Student::getName))
                        .collect(Collectors.toList()));

        thread.start();
        System.out.printf("Inside Main\n");

        Thread thread1 = new Thread(()-> System.out.println("Inside Lambda"));
        thread1.start();

        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        System.out.println(integerComparator.compare(3,2));


        Comparator<Integer> integerComparator1 = (a,b)-> a.compareTo(b);

        System.out.println(integerComparator1.compare(3,2));

        Consumer<String> consumer = (t) -> System.out.println(t);
        consumer.andThen(consumer).andThen(consumer).accept("print twice");

         List<String> students = Arrays.asList("ankit", "bhatt");

        BiConsumer<String,String> biConsumer = (x,y)-> System.out.println(x+"loves"+y);
       // biConsumer.accept("one","two");

        students.forEach(x-> biConsumer.accept(x,x));

        Predicate<String> predicate = x -> x.startsWith("a");
        Predicate<String> predicate1 = x -> x.contains("t");
        Predicate<String> combined = predicate1.or(predicate);

        students.forEach( x-> System.out.println( combined.test(x)));

        Function<String,String> function = (x)-> x.toUpperCase();
        Function<String,String> function1 = (x)-> x.concat("pass");

        System.out.println(function.andThen(function1).apply("ankit bhatt"));
        System.out.println(function.compose(function1).apply("ankit bhatt"));

        List<String> students1 = Arrays.asList("ankit", "bhatt");

        Function<String, String> test12 = String::toUpperCase;
        Function<String, String> test1 = String::toUpperCase;
        System.out.println(test12.apply("taun taun"));

        testFunc(x->x.toUpperCase(),"ankit");
        testFunc(x->x.toLowerCase(),"ankit");

    }

    static String testFunc(Function<String,String> function,String val){
       return function.apply(val);
    }


        List<String> list = getStudents().stream()
                .filter(student -> student.getGpa() > 3.0)
                .filter(student -> student.getGender().equals("male"))
                .flatMap(student -> student.getActivities().stream())
                .distinct()
                .collect(Collectors.toList());
//                .map(student -> new Bike(student.getName(), student.getGender()))
//                // .collect(Collectors.toMap(x-> x.getName(),y->y.getGpa()));
//                .map(Bike::getName)
//                .map(String::toUpperCase)
//                .collect(Collectors.toList());
//        System.out.println(list);
//
//        System.out.println(getStudents()
//                .stream()
//                .collect(Collectors.summingInt(student->student.getNoteBooks())));
//        System.out.println(getStudents().stream()
//                .collect(Collectors.groupingBy(x->x.getName())));

        Map<String,List<Student>> map = getStudents().stream()
                .collect(Collectors.groupingBy(Student::getName
                                ,Collectors.toList()));

       LinkedHashMap<String,List<Student>> map1 =  getStudents().stream()
                        .collect(Collectors.groupingBy(
                                Student::getName , LinkedHashMap::new , Collectors.toList()
                        ));
       Map<Integer,Student> map12=  getStudents().stream()
                        .collect(Collectors.groupingBy(
                                student -> student.getGradeLevel() , Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Student::getGradeLevel)) , Optional::get)
                        ));

 //        System.out.println(map.toString());


    }

