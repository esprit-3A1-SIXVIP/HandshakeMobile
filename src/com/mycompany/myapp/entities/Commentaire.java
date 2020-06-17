
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.compat.java.util.Objects;
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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ghost
 */
public class Commentaire {

    private User user;
    private Question question;
    private String texteCommentaire;
    private Date DateCommentaire;
    private int score;

    public Commentaire() {
    }

    public Commentaire(User user, Question question, String texteCommentaire) {
        this.user = user;
        this.question = question;
        this.texteCommentaire = texteCommentaire;

    }

    public Commentaire(User user, Question Q, String texteCommentaire, int score, Date dateCommentaire) {
        this.user = user;
        this.question = Q;
        this.texteCommentaire = texteCommentaire;
        this.DateCommentaire = dateCommentaire;
        this.score = score;
    }

    public Commentaire(User user, Question question, String texteCommentaire, Date DateCommentaire) {
        this.user = user;
        this.question = question;
        this.texteCommentaire = texteCommentaire;
        this.DateCommentaire = DateCommentaire;
    }

    public Commentaire(User user, Question question, String texteCommentaire, Date DateCommentaire, int score) {
        this.user = user;
        this.question = question;
        this.texteCommentaire = texteCommentaire;
        this.DateCommentaire = DateCommentaire;
        this.score = score;
    }

    public void parse(String s) {
        try {

            this.texteCommentaire = (StringUtil.replaceAll((s.substring((s.indexOf(",texteCommentaire=") + ",texteCommentaire=".length()), s.indexOf(",dateCommentaire="))), "'", "`"));

            this.DateCommentaire = (new SimpleDateFormat("yyyy-MM-dd").parse(s.substring(s.indexOf(",dateCommentaire=") + ",dateCommentaire=".length(), s.indexOf(",userId="))));

            this.score = (Integer.parseInt(s.substring((s.indexOf(",score=") + ",score=".length()), s.length())));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(int id, String TC, long uid) {
        String cnxCC = "http://localhost/mobileshakehub/updatecommentaire.php";
        ConnectionRequest rupdateC = new ConnectionRequest();
        rupdateC.setUrl(cnxCC);
        rupdateC.setPost(false);
        rupdateC.addArgument("id", id + "");
        rupdateC.addArgument("tc", StringUtil.replaceAll(TC, "'", "`"));
        rupdateC.addArgument("uid", uid + "");
        NetworkManager.getInstance().addToQueue(rupdateC);

        rupdateC.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(rupdateC.getResponseData()));

                Dialog.show("Reshaked", ("You have successfully edited your Reply."), null, TYPE_INFO, null, 2000);

            }
        });
    }

    public void delete(int id, long uid, String tc) {
        String cnxUpd = "http://localhost/mobileshakehub/deletecommentaire.php";
        ConnectionRequest requetedelc = new ConnectionRequest();
        requetedelc.setUrl(cnxUpd);
        requetedelc.setPost(false);
        requetedelc.addArgument("id", id + "");
        requetedelc.addArgument("uid", uid + "");
        requetedelc.addArgument("tc", tc + "");
        NetworkManager.getInstance().addToQueue(requetedelc);
        requetedelc.addResponseListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Shaken", ("You have successfully deleted your Reply."), null, TYPE_INFO, null, 2000);
                System.out.println(new String(requetedelc.getResponseData()));

            }
        });

    }

    public void setDateCommentaire(Date DateCommentaire) {
        this.DateCommentaire = DateCommentaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTexteCommentaire() {
        return texteCommentaire;
    }

    public void setTexteCommentaire(String texteCommentaire) {
        this.texteCommentaire = texteCommentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "user=" + user + ", question=" + question + ", texteCommentaire=" + texteCommentaire + ", DateCommentaire=" + DateCommentaire + ", score=" + score + '}';
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
        final Commentaire other = (Commentaire) obj;
        if (!Objects.equals(this.texteCommentaire, other.texteCommentaire)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }

        return true;
    }

    public Date getDateCommentaire() {
        return DateCommentaire;
    }

    public void shake(int questionId, int sc, long uid) {
        String cnxup = "http://localhost/mobileshakehub/shakecommentaire.php";
        ConnectionRequest ruc = new ConnectionRequest();
        ruc.setUrl(cnxup);
        ruc.setPost(false);
        ruc.addArgument("id", questionId + "");
        ruc.addArgument("uid", uid + "");
        ruc.addArgument("sc", sc + "");
        NetworkManager.getInstance().addToQueue(ruc);

        ruc.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evtc) {
                Dialog.show("Shaken!", ("Thank you for your contribution!"), null, TYPE_INFO, null, 1000);

            }
        });
    }

}
