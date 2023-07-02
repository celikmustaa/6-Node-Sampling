public class Sample {
    // nov-wiki: 85469928L, bag-nips: 7325274840L, komarix-citeseer: 1005817L, amazon-ratings: 35849304L, ja-wiki: , live-journal: , orkut: 22100000000000L
    public static long CYCLE_COUNT = 7325274840L;
    public static long FOUR_PATH_COUNT = 0;
    // 6-Vertex Samplings

    //NBNM-52
    public static double cycleM1Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM1Count(cycle, graph);
            final_result += count;
        }
        return final_result / k * CYCLE_COUNT / 9;
    }

    //ADPM-73
    public static double cycleM2Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM2Count(cycle, graph);

            final_result += count;
        }
        return final_result / k * CYCLE_COUNT / 6;
    }

    //FBNM-74
    public static double cycleM3Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM3Count(cycle, graph);

            final_result += count;
        }
        return final_result / k * CYCLE_COUNT / 5;
    }

    //ABPM-90
    public static double cycleM4Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM4Count(cycle, graph);

            final_result += count;
        }
        return final_result / k * CYCLE_COUNT / 3;
    }

    //BBNM-91
    public static double cycleM5Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM5Count(cycle, graph);

            final_result += count;
        }
        return final_result / k * CYCLE_COUNT / 3;
    }

    //IGFK-92
    public static double cycleM6Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM6Count(cycle, graph);

            final_result += count;
        }
        return final_result / k * CYCLE_COUNT / 2;
    }

    //AAPM-101
    public static double cycleM7Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM7Count(cycle, graph);

            final_result += count;
        }
        return final_result / k * CYCLE_COUNT;
    }

    //AGJG-102
    public static double cycleM8Sample(int sample, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < sample; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM8Count(cycle, graph);


            final_result += count;
        }
        return (final_result / sample) * CYCLE_COUNT;
    }

    //ACNM-103
    public static double cycleM9Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM9Count(cycle, graph);


            final_result += count;
        }
        return final_result / k * CYCLE_COUNT;
    }

    //BBJM-104
    public static double cycleM10Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            Cycle cycle = graph.getRandomCycle(graph);

            long count;

            count = ExactCountNonInduced.cycleM10Count(cycle, graph);


            final_result += count;
        }
        return final_result / k * CYCLE_COUNT;
    }


    //MBEM-106
    public static double fourPathM11Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM11Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT / 6;
    }

    //AADO-107
    public static double fourPathM12Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM12Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT;
    }

    //AAHK-108
    public static double fourPathM13Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM13Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT / 3;
    }

    //ACFK-109
    public static double fourPathM14Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM14Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT / 4;
    }

    //AIKG-110
    public static double fourPathM15Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM15Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT / 4;
    }

    //AAPE-111
    public static double fourPathM16Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM16Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT / 3;
    }

    //AGJE-112
    public static double fourPathM17Sample(int k, BipartiteGraph graph) {
        double final_result = 0;
        for (int i = 0; i < k; i++) {
            FourPath fourPath = graph.getRandomFourPath(graph);

            long count;

            count = ExactCountNonInduced.fourPathM17Count(fourPath, graph);


            final_result += count;
        }
        return final_result / k * FOUR_PATH_COUNT / 3;
    }

}

