/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import static com.codename1.ui.Dialog.TYPE_INFO;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author ghost
 */
public class Question {

    public int questionId;
    private String texteQuestion;
    private Date dateQuestion;
    private User user;
    private int score;

    public Question() {
    }

    public Question(int questionId, String texteQuestion, Date dateQuestion, User user) {
        this.questionId = questionId;
        this.texteQuestion = texteQuestion;
        this.dateQuestion = dateQuestion;
        this.user = user;
    }

    public Question(int questionId, String texteQuestion, Date dateQuestion, User user, int score) {
        this.questionId = questionId;
        this.texteQuestion = texteQuestion;
        this.dateQuestion = dateQuestion;
        this.user = user;
        this.score = score;
    }

    public Question(Question Q) {
        this.questionId = Q.questionId;
        this.texteQuestion = Q.texteQuestion;
        this.dateQuestion = Q.dateQuestion;
        this.user = Q.user;
        this.score = Q.score;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public Date getDateQuestion() {
        return dateQuestion;
    }

    public void setDateQuestion(Date dateQuestion) {
        this.dateQuestion = dateQuestion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void parse(String s) {
        try {
        this.questionId=(Integer.parseInt(s.substring((s.indexOf("questionId=") + "questionId=".length()), s.indexOf(",texteQuestion="))));
        
        this.texteQuestion=StringUtil.replaceAll(s.substring(((s.indexOf("texteQuestion=")) + ("texteQuestion=".length())), (s.indexOf(",dateQuestion="))), "'", "`");
        
            this.dateQuestion=(new SimpleDateFormat("yyyy-MM-dd").parse(s.substring((s.indexOf("dateQuestion=") + "dateQuestion=".length()), s.indexOf(",userId="))));
        
        this.score=(Integer.parseInt(s.substring((s.indexOf(",score=") + ",score=".length()), s.length())));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(int id, String TQ, long uid) {
        String cnxUpd = "http://localhost/mobileshakehub/updatequestion.php";
        ConnectionRequest requeteUpd = new ConnectionRequest();
        requeteUpd.setUrl(cnxUpd);
        requeteUpd.setPost(false);
        requeteUpd.addArgument("id", id + "");
        requeteUpd.addArgument("tq", StringUtil.replaceAll(TQ, "'", "`"));
        requeteUpd.addArgument("uid", uid + "");
        NetworkManager.getInstance().addToQueue(requeteUpd);
        requeteUpd.addResponseListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println(new String(requeteUpd.getResponseData()));
                Dialog.show("Reshaked", ("You have successfully edited your Question."), null, TYPE_INFO, null, 2000);
                
            }
        });
        
    }
    public void delete(int id, long uid) {
        String cnxUpd = "http://localhost/mobileshakehub/deletequestion.php";
        ConnectionRequest requetedelQ = new ConnectionRequest();
        requetedelQ.setUrl(cnxUpd);
        requetedelQ.setPost(false);
        requetedelQ.addArgument("id", id + "");
        requetedelQ.addArgument("uid", uid + "");
        NetworkManager.getInstance().addToQueue(requetedelQ);
        requetedelQ.addResponseListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Shaken", ("You have successfully deleted your Question."), null, TYPE_INFO, null, 2000);
                System.out.println(new String(requetedelQ.getResponseData()));
            }
        });
        
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ",texteQuestion=" + texteQuestion + ",dateQuestion=" + dateQuestion + ",user=" + user + ",score=" + score + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.questionId;
        hash = 59 * hash + Objects.hashCode(this.texteQuestion);
        hash = 59 * hash + Objects.hashCode(this.dateQuestion);
        hash = 59 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        if (this.questionId != other.questionId) {
            return false;
        }
        if (!Objects.equals(this.texteQuestion, other.texteQuestion)) {
            return false;
        }
        if (!Objects.equals(this.dateQuestion, other.dateQuestion)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    public void shake(int questionId,int sc) {
        String cnxup = "http://localhost/mobileshakehub/shakequestion.php";
        ConnectionRequest ruq = new ConnectionRequest();
        ruq.setUrl(cnxup);
        ruq.setPost(false);
        ruq.addArgument("id", questionId + "");
        ruq.addArgument("sc", sc+ "");
        NetworkManager.getInstance().addToQueue(ruq);

        ruq.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evtq) {
                Dialog.show("Shaken!", ("Thank you for your contribution!"), null, TYPE_INFO, null, 1000);

            }
        });
    }

    public void ban(User user) {
String cnxup = "http://localhost/mobileshakehub/ban.php";
        ConnectionRequest rb = new ConnectionRequest();
        rb.setUrl(cnxup);
        rb.setPost(false);
        rb.addArgument("id", user.getUserId() + "");
        NetworkManager.getInstance().addToQueue(rb);

        rb.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evtq) {
                Dialog.show("Banned User", ("You have successfully banned "+user.getLogin()), null, TYPE_INFO, null, 2000);

            }
        });    }

}
