package world.hello.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.collection.ArrayMap;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `" + DBConstants.TABLE_QUESTION_NAME+ "` ( " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_ID+"` INT NOT NULL, " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_LABEL +"` TEXT(255) NOT NULL, " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_ANSWER1+"` TEXT(255) NOT NULL, " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_ANSWER2+"` TEXT(255) NOT NULL, " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_ANSWER3+"` TEXT(255) NOT NULL, " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_ANSWER4+"` TEXT(255) NOT NULL, " +
                " `"+ DBConstants.TABLE_QUESTION_FIELD_CORRECTANSWER+"` INT NOT NULL, " +
                " PRIMARY KEY (`"+DBConstants.TABLE_QUESTION_FIELD_ID+"`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `" + DBConstants.TABLE_RESULT_NAME+ "` ( " +
                " `"+ DBConstants.TABLE_RESULT_FIELD_ID+"` INT NOT NULL, " +
                " `"+ DBConstants.TABLE_RESULT_FIELD_NAME +"` TEXT(255) NOT NULL, " +
                " `"+ DBConstants.TABLE_RESULT_FIELD_CORRECT_ANSWER+"` INT NOT NULL, " +
                " `"+ DBConstants.TABLE_RESULT_FIELD_TOTAL_QUESTIONS+"` INT NOT NULL, " +
                " PRIMARY KEY (`"+DBConstants.TABLE_RESULT_FIELD_ID+"`))");
        this.doPrepareQuestions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DBConstants.TABLE_QUESTION_NAME);
        db.execSQL("drop table if exists " + DBConstants.TABLE_RESULT_NAME);
    }

    //TABLE METHODS
    public List<Question> getQuestions(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Question> returnStructure = new ArrayList<>();
        Boolean doit = true;
        while(doit) {

            Cursor dbCursor = db.query(DBConstants.TABLE_QUESTION_NAME, null, null, null, null, null, null);
            dbCursor.moveToFirst();

            Integer index_id = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_ID);
            Integer index_label = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_LABEL);
            Integer index_ans1 = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_ANSWER1);
            Integer index_ans2 = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_ANSWER2);
            Integer index_ans3 = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_ANSWER3);
            Integer index_ans4 = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_ANSWER4);
            Integer index_correctans = dbCursor.getColumnIndex(DBConstants.TABLE_QUESTION_FIELD_CORRECTANSWER);
            while (!dbCursor.isAfterLast()){
                Question newQuestion = new Question(
                        dbCursor.getString(index_label),
                        dbCursor.getString(index_ans1),
                        dbCursor.getString(index_ans2),
                        dbCursor.getString(index_ans3),
                        dbCursor.getString(index_ans4),
                        dbCursor.getInt(index_correctans)
                );
                returnStructure.add(newQuestion);
                dbCursor.moveToNext();
            }

            if (returnStructure.size() > 0) doit = false;
            else doPrepareQuestions(db);
        }
        db.close();
        return returnStructure;
    }

    private void doPrepareQuestions(SQLiteDatabase db){
        List<String> Questions = new ArrayList<String>();

        List<String> Answers1 = new ArrayList<String>();
        List<String> Answers2 = new ArrayList<String>();
        List<String> Answers3 = new ArrayList<String>();
        List<String> Answers4 = new ArrayList<String>();
        List<Integer> CorrectAnswers = new ArrayList<Integer>();

        Questions.add("Что не относится к основным принципам ООП?");
        Answers1.add("Импликация");
        Answers2.add("Инкапсуляция");
        Answers3.add("Полиморфизм");
        Answers4.add("Все относится");
        CorrectAnswers.add(1);

        Questions.add("Как общаются между собой объекты внутри программы?");
        Answers1.add("По средствам отправки уведомлений");
        Answers2.add("По средствам отправки HTTP-запросов");
        Answers3.add("По средствам отправки SQL-запросов");
        Answers4.add("По средствам отправки сообщений");
        CorrectAnswers.add(4);

        Questions.add("Чем описывается объект в ООП?");
        Answers1.add("Состоянием");
        Answers2.add("Наследованием");
        Answers3.add("Композицией");
        Answers4.add("Конструктором");
        CorrectAnswers.add(1);

        Questions.add("Что такое класс в ООП?");
        Answers1.add("Класс это специальный тип данных");
        Answers2.add("Класс это чертёж по которому может быть создан объект");
        Answers3.add("Класс используется для выделения памяти");
        Answers4.add("Ничего из перечисленного");
        CorrectAnswers.add(2);

        Questions.add("В чем разница между идентичностью и равенством?");
        Answers1.add("У объектов одинаковые поля, а равенство – что они (объекты) содержат одинаковые данные");
        Answers2.add("Две ссылки указывают на один и тот же объект, а равенство – что они (объекты) содержат одинаковые данные");
        Answers3.add("Объекты являются экземплярами одного и того же класса, а равенство – что они (объекты) содержат одинаковые данные");
        Answers4.add("У объектов есть общий не абстрактный предок, а равенство – любой общий предок");
        CorrectAnswers.add(2);

        Questions.add("В чем разница между объектом и классом?");
        Answers1.add("Класс - это экземпляр объекта");
        Answers2.add("Классом всегда является более общее понятие, а объектом – более конкретное");
        Answers3.add("Объектом всегда является более общее понятие, а классом – более конкретное.");
        Answers4.add("Класс – это исходный код, а объект – скомпилированный и выполняемый код");
        CorrectAnswers.add(2);

        Questions.add("Что такое инкапсуляция?");
        Answers1.add("Сокрытие реализации");
        Answers2.add("Сокрытие данных");
        Answers3.add("Объединение кода и данных внутри объекта");
        Answers4.add("Изменение поведения дочерних объектов");
        CorrectAnswers.add(3);

        Questions.add("Чем представляется программа в ООП?");
        Answers1.add("Набор объектов");
        Answers2.add("Последовательность термов");
        Answers3.add("Поседовательность функций");
        Answers4.add("События и их обработчики");
        CorrectAnswers.add(1);

        Questions.add("Какой из принципов в ООП говорит о необходимости отказаться от безосновательного повторения кода?");
        Answers1.add("SOLID");
        Answers2.add("DRY");
        Answers3.add("KISS");
        Answers4.add("GRASP");
        CorrectAnswers.add(2);

        Questions.add("Как в терминах ООП называется объект, состояние которого может быть изменено после создания?");
        Answers1.add("static");
        Answers2.add("mutable");
        Answers3.add("internal");
        Answers4.add("dynamic");
        CorrectAnswers.add(2);
        for(int i = 0; i < Questions.size(); i ++){
            ContentValues values = new ContentValues();
            values.put(DBConstants.TABLE_QUESTION_FIELD_ID, i+1);
            values.put(DBConstants.TABLE_QUESTION_FIELD_LABEL ,Questions.get(i));
            values.put(DBConstants.TABLE_QUESTION_FIELD_ANSWER1 ,Answers1.get(i));
            values.put(DBConstants.TABLE_QUESTION_FIELD_ANSWER2 ,Answers2.get(i));
            values.put(DBConstants.TABLE_QUESTION_FIELD_ANSWER3 ,Answers3.get(i));
            values.put(DBConstants.TABLE_QUESTION_FIELD_ANSWER4 ,Answers4.get(i));
            values.put(DBConstants.TABLE_QUESTION_FIELD_CORRECTANSWER ,CorrectAnswers.get(i));
            long isCreated = db.insert(DBConstants.TABLE_QUESTION_NAME, null, values);
            System.console();
        }

    }

    //RESULT METHODS
    public List<String> getAllResults(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> returnStructure = new ArrayList<>();
        Cursor dbCursor = db.query(DBConstants.TABLE_RESULT_NAME, null, null, null, null, null, null);
        dbCursor.moveToFirst();
        Integer index_id = dbCursor.getColumnIndex(DBConstants.TABLE_RESULT_FIELD_ID);
        Integer index_name = dbCursor.getColumnIndex(DBConstants.TABLE_RESULT_FIELD_NAME);
        Integer index_correctans = dbCursor.getColumnIndex(DBConstants.TABLE_RESULT_FIELD_CORRECT_ANSWER);
        Integer index_total = dbCursor.getColumnIndex(DBConstants.TABLE_RESULT_FIELD_TOTAL_QUESTIONS);
        while (!dbCursor.isAfterLast()){
            returnStructure.add(dbCursor.getString(index_name));
            returnStructure.add(dbCursor.getInt(index_correctans) + "/" + dbCursor.getInt(index_total));
            dbCursor.moveToNext();
        }
        db.close();
        return returnStructure;
    }

    public void addNewResult(String name, int correctans, int totalquestions){
        Integer indexOfLastElement = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor dbCursor = db.query(DBConstants.TABLE_RESULT_NAME, new String[]{DBConstants.TABLE_RESULT_FIELD_ID}, null, null, null, null, DBConstants.TABLE_RESULT_FIELD_ID + " DESC", "1");
        dbCursor.moveToFirst();
        Integer index_id = dbCursor.getColumnIndex(DBConstants.TABLE_RESULT_FIELD_ID);
        if(!dbCursor.isAfterLast()) indexOfLastElement = dbCursor.getInt(index_id) + 1;

        ContentValues values = new ContentValues();
        values.put(DBConstants.TABLE_RESULT_FIELD_ID, indexOfLastElement);
        values.put(DBConstants.TABLE_RESULT_FIELD_NAME ,name);
        values.put(DBConstants.TABLE_RESULT_FIELD_CORRECT_ANSWER ,correctans);
        values.put(DBConstants.TABLE_RESULT_FIELD_TOTAL_QUESTIONS ,totalquestions);
        db.insert(DBConstants.TABLE_RESULT_NAME, null, values);
        db.close();
    }

}
