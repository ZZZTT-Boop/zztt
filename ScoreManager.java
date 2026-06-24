import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 学生成绩实体类
class Score {
    private String name;    // 姓名
    private String id;      // 学号
    private String course;  // 课程名
    private double score;   // 成绩

    // 构造方法
    public Score(String name, String id, String course, double score) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.score = score;
    }

    // getter方法
    public String getName() { return name; }
    public String getId() { return id; }
    public String getCourse() { return course; }
    public double getScore() { return score; }

    // 打印单条成绩信息
    public void printInfo() {
        System.out.printf("姓名：%s，学号：%s，课程：%s，成绩：%.1f%n",
                name, id, course, score);
    }
}

public class ScoreManager {
    // 存储所有成绩记录
    private static List<Score> scoreList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // 打印主菜单
            printMainMenu();
            System.out.print("请输入选项序号：");
            int op = sc.nextInt();
            sc.nextLine(); // 吸收换行符

            switch (op) {
                case 1:
                    addScore();
                    break;
                case 2:
                    queryScore();
                    break;
                case 3:
                    statScore();
                    break;
                case 4:
                    System.out.println("系统退出，再见！");
                    sc.close();
                    return;
                default:
                    System.out.println("输入序号无效，请重新选择！");
            }
            System.out.println("----------------------------------------");
        }
    }

    // 打印主菜单
    private static void printMainMenu() {
        System.out.println("============【菜单示例】============");
        System.out.println("        欢迎使用学生成绩管理系统");
        System.out.println("====================================");
        System.out.println("请选择操作：");
        System.out.println("1. 记录学生成绩");
        System.out.println("2. 查询学生成绩");
        System.out.println("3. 统计课程成绩");
        System.out.println("4. 退出系统");
        System.out.println();
    }

    // 1. 录入学生成绩
    private static void addScore() {
        System.out.println("========【记录学生成绩】========");
        System.out.println("===== 记录学生成绩 =====");

        System.out.print("请输入学生姓名：");
        String name = sc.nextLine();

        System.out.print("请输入学生学号：");
        String id = sc.nextLine();

        System.out.print("请输入课程名称：");
        String course = sc.nextLine();

        double score;
        while (true) {
            System.out.print("请输入成绩(0-100)：");
            score = sc.nextDouble();
            sc.nextLine();
            if (score >= 0 && score <= 100) break;
            System.out.println("成绩超出范围，请输入0-100之间的数字！");
        }

        // 添加记录
        scoreList.add(new Score(name, id, course, score));
        System.out.println("成绩已成功记录！");
    }

    // 2. 查询成绩（3种查询方式）
    private static void queryScore() {
        System.out.println("========【查询学生成绩】========");
        System.out.println("===== 查询学生成绩 =====");
        System.out.println("请选择查询方式：");
        System.out.println("1. 按学生姓名查询");
        System.out.println("2. 按学生学号查询");
        System.out.println("3. 按课程名称查询");
        System.out.print("请输入选项序号：");
        int queryOp = sc.nextInt();
        sc.nextLine();

        boolean hasResult = false;
        switch (queryOp) {
            case 1:
                System.out.print("请输入学生姓名：");
                String qName = sc.nextLine();
                for (Score s : scoreList) {
                    if (s.getName().equals(qName)) {
                        s.printInfo();
                        hasResult = true;
                    }
                }
                break;
            case 2:
                System.out.print("请输入学生学号：");
                String qId = sc.nextLine();
                for (Score s : scoreList) {
                    if (s.getId().equals(qId)) {
                        s.printInfo();
                        hasResult = true;
                    }
                }
                break;
            case 3:
                System.out.print("请输入课程名称：");
                String qCourse = sc.nextLine();
                for (Score s : scoreList) {
                    if (s.getCourse().equals(qCourse)) {
                        s.printInfo();
                        hasResult = true;
                    }
                }
                break;
            default:
                System.out.println("查询序号无效！");
                return;
        }
        if (!hasResult) {
            System.out.println("未查询到匹配的成绩记录！");
        }
    }

    // 3. 统计课程成绩：平均分、最高分、最低分
    private static void statScore() {
        System.out.println("========【统计课程成绩】========");
        System.out.print("请输入要统计的课程名称：");
        String statCourse = sc.nextLine();

        List<Double> tempScores = new ArrayList<>();
        for (Score s : scoreList) {
            if (s.getCourse().equals(statCourse)) {
                tempScores.add(s.getScore());
            }
        }

        if (tempScores.isEmpty()) {
            System.out.println("该课程暂无成绩数据！");
            return;
        }

        // 计算统计值
        double sum = 0, max = tempScores.get(0), min = tempScores.get(0);
        for (double num : tempScores) {
            sum += num;
            if (num > max) max = num;
            if (num < min) min = num;
        }
        double avg = sum / tempScores.size();

        System.out.println("课程【" + statCourse + "】统计结果：");
        System.out.printf("总人数：%d 人%n", tempScores.size());
        System.out.printf("平均分：%.2f%n", avg);
        System.out.printf("最高分：%.1f%n", max);
        System.out.printf("最低分：%.1f%n", min);
    }
}