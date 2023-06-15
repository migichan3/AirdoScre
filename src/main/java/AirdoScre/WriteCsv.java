package src.main.java.AirdoScre;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class WriteCsv {

    //Calendarクラスのオブジェクトを生成する
    private static final Calendar cl = Calendar.getInstance();
    //SimpleDateFormatクラスでフォーマットパターンを設定する
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
    private static final String CSV_NAME = sdf.format(cl.getTime()) + ".csv";

    FileWriter fw;
    PrintWriter pw;

    public WriteCsv() throws IOException{
        fw = new FileWriter(CSV_NAME, false);
        pw = new PrintWriter(new BufferedWriter(fw));

        // 初期のヘッダーの指定
        pw.print("日付");
        pw.print(",");
        pw.print("時間");
        pw.print(",");
        pw.print("空き状況");
        pw.println();
    }
    
    public void exportCsv(String nowSearchDate, String timeFromTo, String numVacant){
 
        pw.print(nowSearchDate);
        pw.print(",");
        pw.print(timeFromTo);
        pw.print(",");
        pw.print(numVacant);
        pw.println();
    }

    public void finishExportCsv(){
        // ファイルを閉じる
        pw.close();
        // 出力確認用のメッセージ
        System.out.println("csvファイルを出力しました");
    }

}
