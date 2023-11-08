package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
   private int lastQuotionId;
    private List<Quotation> quotations;
    private Scanner sc;

    public App() {
        lastQuotionId = 0;
        quotations = new ArrayList<>();
        sc = new Scanner(System.in);

    }

   public void run() {
        System.out.println("== 명언 앱 == ");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine(); //한줄이 입력될떄까지 정지
            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }
        }
    }


    private void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String authorName = sc.nextLine();

        lastQuotionId++;
        int id = lastQuotionId;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("%d번 명언이 등록되었습니다 \n", lastQuotionId);
    }

   private void actionList() {
        System.out.println("번호 / 명언 / 작가");
        System.out.println("---------------------");

        Quotation quotation;

        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = quotations.size() - 1; i >= 0; i--) {
            quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.content, quotation.authorName);

        }
    }

   private void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if( id == 0){
            System.out.println("id를 입력해주세요");
            return;
        }
        int index = findQuotationIndexById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotations.remove(index);

        System.out.printf("%d번 명언을 삭제되었습니다.\n", id);
    }

   private int findQuotationIndexById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.id == id) {
                return i;
            }
        }

        return -1;

    }

   private void actionModify(Rq rq) {

        int id = rq.getParamAsInt("id", 0);

        if( id == 0){
            System.out.println("id를 입력해주세요");
            return;
        }
       int index = findQuotationIndexById(id);

       if (index == -1) {
           System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
           return;
       }

       Quotation quotation = quotations.get(index);

       System.out.printf("명언(기존) : %s\n", quotation.content);
       System.out.print("명언 : ");
       String content = sc.nextLine();

       System.out.printf("작가(기존) : %s\n", quotation.authorName);
       System.out.print("작가 : ");
       String authorName = sc.nextLine();

       quotation.content = content;
       quotation.authorName = authorName;

       System.out.printf("%d번 명언을 수정되었습니다.\n", id);
    }

}
