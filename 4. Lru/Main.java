import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Main {

    public static void main(String[] args) {
        Cache cache = new Cache(5);

        for (int i = 0; i < 10; i++) {
            Pair pair = new Pair(i % 5, i);
            cache.add(pair);
        }
    }
}
