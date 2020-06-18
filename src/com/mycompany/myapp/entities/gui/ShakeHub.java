/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui;

import com.codename1.charts.compat.Paint;
import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import static com.codename1.charts.util.ColorUtil.BLUE;
import static com.codename1.charts.util.ColorUtil.GREEN;
import static com.codename1.charts.util.ColorUtil.MAGENTA;
import com.codename1.util.StringUtil;
import static com.codename1.charts.util.ColorUtil.YELLOW;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import java.text.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.Dialog.TYPE_INFO;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ComponentStateChangeEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.encloseYCenter;
import static com.codename1.ui.layouts.BoxLayout.y;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class ShakeHub {

    private Form current;
    private Resources theme;
    int i, j;
    private EncodedImage ec = null;

    public ShakeHub() {
    }

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start(User U) {
        if (current != null) {
            current.show();
            return;
        }
        String cnxQuestion = "http://localhost/mobileshakehub/getquestion.php";
        String cnx = "http://localhost/mobileshakehub/cnx.php";
        String cnx1 = "http://localhost/mobileshakehub/cnx1.php";
        String cnxUser = "http://localhost/mobileshakehub/getuser.php";

        String cnxCommentaire = "http://localhost/mobileshakehub/getcommentaire.php";
        String cnxSubmitQ = "http://localhost/mobileshakehub/submitquestion.php";
        String cnxSubmitC = "http://localhost/mobileshakehub/submitcommentaire.php";
        int softOrange = new ColorUtil().rgb(228, 184, 11);
        int softBlue = new ColorUtil().rgb(120, 144, 238);
        int darkBlue = new ColorUtil().rgb(81, 113, 242);
        int darkGreen = new ColorUtil().rgb(57, 171, 64);
        int softRed = new ColorUtil().rgb(223, 112, 112);
        int softGreen = new ColorUtil().rgb(141, 212, 144);
        int darkYellow = new ColorUtil().rgb(228, 201, 94);
        int QBGYellow = new ColorUtil().rgb(235, 222, 173);
        int CBGBlue = new ColorUtil().rgb(214, 219, 239);

        Form S = new Form("Welcome to the ShakeHub", BoxLayout.y());

        if (U.isAccesShakeHub() == 1 || U.getRole().equals("a:1:{i:0;s:5:\"ADMIN\";}")) {

            ConnectionRequest requete2 = new ConnectionRequest();
            requete2.setUrl(cnx1);
            requete2.setPost(false);
            NetworkManager.getInstance()
                    .addToQueue(requete2);
            requete2.addResponseListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt2) {

                    String res2 = new String(requete2.getResponseData());

                    i = res2.indexOf("Id Question: ");

                    while (i != -1) {

                        ConnectionRequest requeteQuestion = new ConnectionRequest();
                        requeteQuestion.setUrl(cnxQuestion);
                        requeteQuestion.setPost(false);
                        String qId = res2.substring(res2.indexOf("Id Question: ", i) + "Id Question: ".length(), res2.indexOf("- Question: ", i));
                        requeteQuestion.addArgument("id", qId);
                        String uid = res2.substring(res2.indexOf("- User Question: ", i) + "- User Question: ".length(), res2.indexOf("- Date Question: ", i));

                        requeteQuestion.addArgument("uid", uid);
                        NetworkManager.getInstance()
                                .addToQueue(requeteQuestion);

                        requeteQuestion.addResponseListener(new ActionListener() {
                            public void actionPerformed(ActionEvent evt3) {
                                String resQ = new String(requeteQuestion.getResponseData());
                                Question QL = new Question();
                                QL.parse(resQ);
                                ConnectionRequest rgetuser = new ConnectionRequest();
                                rgetuser.setUrl(cnxUser);
                                rgetuser.setPost(false);
                                rgetuser.addArgument("id", uid);
                                NetworkManager.getInstance()
                                        .addToQueue(rgetuser);
                                rgetuser.addResponseListener(new ActionListener<NetworkEvent>() {
                                    @Override
                                    public void actionPerformed(NetworkEvent evt4) {
                                        String resU = new String(rgetuser.getResponseData());

                                        User user = new User();

                                        user.parse(resU);

                                        QL.setUser(user);

                                        Label Username = new Label(QL.getUser().getLogin());
                                        try {
                                            Label profilQ;

                                            try {
                                                ec = EncodedImage.create("/load.jpg");
                                            } catch (IOException ex) {
                                                System.out.println(ex.getMessage());
                                            }
                                            if (!(QL.getUser().getProfil().equals(null))) {
                                                profilQ = new Label(URLImage.createToStorage(ec, "http://localhost/mobileshakehub/" + QL.getUser().getProfil(), "http://localhost/mobileshakehub/" + QL.getUser().getProfil(), URLImage.RESIZE_SCALE));
                                                profilQ.setHeight(300);
                                                profilQ.setWidth(300);
                                            } else {
                                                profilQ = new Label(Image.createImage("/usericon.png").scaled(300, 300));
                                            }
                                            profilQ.getAllStyles().setPadding(0, 0, 0, 0);
                                            TextArea TQ = new TextArea(QL.getTexteQuestion());
                                            TQ.getAllStyles().setFgColor(darkBlue);
                                            TQ.getAllStyles().setBgColor(darkYellow);
                                            Label Score = new Label("            " + QL.getScore());
                                            if (QL.getScore() < 0) {
                                                Score.getAllStyles().setFgColor(darkBlue);
                                            } else if (QL.getScore() > 0) {
                                                Score.getAllStyles().setFgColor(darkGreen);
                                            }
                                            Button delete = new Button("Shake Out");

                                            Button upvote = new Button("Shake Up");

                                            upvote.getAllStyles().setFgColor(softOrange);

                                            Button downvote = new Button("Shake Down");
                                            downvote.getAllStyles().setFgColor(softBlue);
                                            int init = QL.getScore();

                                            upvote.addLongPressListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent upp) {

                                                    if (init - QL.getScore() == 0) {
                                                        QL.setScore(QL.getScore() + 1);
                                                        QL.shake(QL.getQuestionId(), QL.getScore());
                                                        upvote.getAllStyles().setFgColor(BLACK);
                                                        downvote.getAllStyles().setFgColor(softBlue);
                                                        downvote.setEnabled(true);
                                                        upvote.setEnabled(false);
                                                        Score.setText("            " + QL.getScore());
                                                        S.revalidate();
                                                    }
                                                }
                                            });
                                            downvote.addLongPressListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent dvv) {
                                                    if (init - QL.getScore() == 0) {
                                                        QL.setScore(QL.getScore() - 1);
                                                        QL.shake(QL.getQuestionId(), QL.getScore());
                                                        downvote.getAllStyles().setFgColor(BLACK);
                                                        upvote.getAllStyles().setFgColor(softOrange);
                                                        downvote.setEnabled(false);
                                                        upvote.setEnabled(true);
                                                        Score.setText("            " + QL.getScore());
                                                        S.revalidate();
                                                    }

                                                }
                                            });

                                            Label date = new Label(QL.getDateQuestion() + "");

                                            Container C3 = BoxLayout.encloseYCenter(upvote, Score, downvote);

                                            Container C2 = BoxLayout.encloseX(C3, TQ);
                                            Container QProfil = BoxLayout.encloseX(profilQ, Username);
                                            Container C1 = BoxLayout.encloseY(QProfil, date, C2);
                                            Label LP = new Label("Tap this when you're done to Reshake!");
                                            LP.getAllStyles().setFgColor(softGreen);

                                            TQ.addDataChangedListener(new DataChangedListener() {

                                                @Override
                                                public void dataChanged(int type, int index) {
                                                    if ((!C1.contains(LP))) {
                                                        C1.add(LP);

                                                    }
                                                }

                                            });
                                            LP.addPointerPressedListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent dq2) {

                                                    C1.removeComponent(LP);
                                                    QL.update(QL.getQuestionId(), TQ.getText(), QL.getUser().getUserId());
                                                    S.revalidate();

                                                }
                                            });

                                            Button Reply = new Button("Shake In");
                                            Reply.getAllStyles().setPadding(0, 0, CENTER, CENTER);
                                            Reply.addActionListener(new ActionListener() {

                                                @Override
                                                public void actionPerformed(ActionEvent back
                                                ) {
                                                    Reply.remove();

                                                    TextArea TCS = new TextField("", "Respect the rules of the ShakeHub.");
                                                    TCS.getAllStyles().setFgColor(darkBlue);
                                                    TCS.getAllStyles().setBgColor(QBGYellow);
                                                    Button SubmitC = new Button("Submit");
                                                    Button CancelC = new Button("Cancel");
                                                    SubmitC.getAllStyles().setFgColor(darkYellow);

                                                    SubmitC.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent evtscs) {
                                                            ConnectionRequest requeteSubmitC = new ConnectionRequest();

                                                            requeteSubmitC.setUrl(cnxSubmitC);
                                                            requeteSubmitC.setPost(false);
                                                            requeteSubmitC.addArgument("id", QL.getQuestionId() + "");
                                                            requeteSubmitC.addArgument("uid", U.getUserId() + "");
                                                            String SX = StringUtil.replaceAll(TCS.getText(), "'", "`");
                                                            requeteSubmitC.addArgument("tc", SX);
                                                            Date DC = new Date(System.currentTimeMillis());
                                                            String xdC = new SimpleDateFormat().format(DC);

                                                            xdC = (("20" + xdC.substring(xdC.lastIndexOf("/"), xdC.lastIndexOf("/", xdC.indexOf(" ")))))
                                                                    + "/"
                                                                    + ((xdC.substring(0, xdC.indexOf("/", 0)).length() == 1) ? "0" + xdC.substring(0, xdC.indexOf("/", 0)) : xdC.substring(0, xdC.indexOf("/", 0)))
                                                                    + "/"
                                                                    + ((xdC.substring(xdC.indexOf("/", 0), xdC.indexOf("/", xdC.indexOf("/", 2))).length() == 1) ? "0" + xdC.substring(xdC.indexOf("/", 0), xdC.indexOf("/", 2)) : xdC.substring(xdC.indexOf("/", 0), xdC.indexOf("/", 2)));
                                                            requeteSubmitC.addArgument("date", xdC);
                                                            NetworkManager.getInstance()
                                                                    .addToQueue(requeteSubmitC);
                                                            requeteSubmitC.addResponseListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent evtsc) {
                                                                    Dialog.show("Success!", ("Thank you for submitting a Reply on ShakeHub " + U.getPrenomUser() + " " + U.getNomUser()) + " !", null, TYPE_INFO, null, 2000);
                                                                    Label UsernameC = new Label(U.getLogin());
                                                                    String resSubmitC = new String(requeteSubmitC.getResponseData());

                                                                    TCS.remove();
                                                                    SubmitC.remove();
                                                                    try {
                                                                        Label profilC;

                                                                        try {
                                                                            ec = EncodedImage.create("/load.jpg");
                                                                        } catch (IOException ex) {
                                                                            System.out.println(ex.getMessage());
                                                                        }
                                                                        if (!(U.getProfil().equals(""))) {
                                                                            profilC = new Label(URLImage.createToStorage(ec, "http://localhost/mobileshakehub/" + U.getProfil(), "http://localhost/mobileshakehub/" + U.getProfil(), URLImage.RESIZE_SCALE));
                                                                            profilC.setHeight(300);
                                                                            profilC.setWidth(300);
                                                                        } else {
                                                                            profilC = new Label(Image.createImage("/usericon.png").scaled(300, 300));
                                                                        }

                                                                        profilC.getAllStyles().setPadding(0, 0, 0, 0);
                                                                        TextArea TCC = new TextArea(TCS.getText());
                                                                        TCC.getAllStyles().setFgColor(BLACK);
                                                                        TCC.getAllStyles().setBgColor(CBGBlue);
                                                                        Label ScoreC = new Label("             0");

                                                                        Button upvoteC = new Button("Shake Up");

                                                                        upvoteC.getAllStyles().setFgColor(softOrange);

                                                                        Button downvoteC = new Button("Shake Down");
                                                                        downvote.getAllStyles().setFgColor(softBlue);

                                                                        Label dateC = new Label(DC + "");

                                                                        Container CC3 = BoxLayout.encloseYCenter(upvoteC, ScoreC, downvoteC);
                                                                        Container CC2 = BoxLayout.encloseX(CC3, TCC);
                                                                        Container CCProfil = BoxLayout.encloseX(profilC, UsernameC);
                                                                        Container CC1 = BoxLayout.encloseY(CCProfil, dateC, CC2);
                                                                        CC1.getStyle()
                                                                                .setPadding(0, 0, 200, 0);
                                                                        CC1.getAllStyles()
                                                                                .setBgColor(CBGBlue);
                                                                        CC1.getAllStyles()
                                                                                .setFgColor(QBGYellow);

                                                                        C1.add(CC1);
                                                                        S.revalidate();

                                                                    } catch (IOException ex) {
                                                                        System.out.println(ex.getMessage());
                                                                    }

                                                                }

                                                            });

                                                        }
                                                    }
                                                    );

                                                    C1.addAll(TCS, SubmitC, CancelC);
                                                    CancelC.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent evtscs) {
                                                            TCS.remove();
                                                            SubmitC.remove();
                                                            CancelC.remove();
                                                            if (!C1.contains(Reply)) {
                                                                C1.add(Reply);
                                                            }
                                                        }

                                                    });
                                                    S.revalidate();

                                                }

                                            });

                                            j = res2.indexOf("Id Commentaire: ");
                                            while (j != -1) {
                                                String idC = res2.substring(res2.indexOf("Id Commentaire: ", j) + "Id Commentaire: ".length(), res2.indexOf("- Commentaire: ", j));

                                                if (idC.equals(qId)) {
                                                    ConnectionRequest requeteCommentaire = new ConnectionRequest();
                                                    requeteCommentaire.setUrl(cnxCommentaire);
                                                    requeteCommentaire.setPost(false);
                                                    String uidC = res2.substring(res2.indexOf("- User Commentaire: ", j) + "- User Commentaire: ".length(), res2.indexOf("- Date Commentaire: ", j));
                                                    String texteCommentaire = StringUtil.replaceAll(res2.substring(res2.indexOf("- Commentaire: ", j) + "- Commentaire: ".length(), res2.indexOf("- User Commentaire: ", j)), "'", "`");

                                                    requeteCommentaire.addArgument("id", idC);
                                                    requeteCommentaire.addArgument("uid", uidC);
                                                    requeteCommentaire.addArgument("tc", texteCommentaire);
                                                    try {
                                                        Date dateC = (new SimpleDateFormat("yyyy-MM-dd").parse(res2.substring(res2.indexOf("- Date Commentaire: ", j) + "- Date Commentaire: ".length(), res2.indexOf("- Score Commentaire: ", j))));
                                                        String argC = new SimpleDateFormat().format(dateC);

                                                        argC = (("20" + argC.substring(argC.lastIndexOf("/"), argC.lastIndexOf("/", argC.indexOf(" ")))))
                                                                + "/"
                                                                + ((argC.substring(0, argC.indexOf("/", 0)).length() == 1) ? "0" + argC.substring(0, argC.indexOf("/", 0)) : argC.substring(0, argC.indexOf("/", 0)))
                                                                + "/"
                                                                + ((argC.substring(argC.indexOf("/", 0), argC.indexOf("/", argC.indexOf("/", 2))).length() == 1) ? "0" + argC.substring(argC.indexOf("/", 0), argC.indexOf("/", 2)) : argC.substring(argC.indexOf("/", 0), argC.indexOf("/", 2)));
                                                        requeteCommentaire.addArgument("date", argC);
                                                    } catch (java.text.ParseException ex) {
                                                        System.out.println(ex.getMessage());
                                                    }

                                                    NetworkManager.getInstance()
                                                            .addToQueue(requeteCommentaire);
                                                    requeteCommentaire.addResponseListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent evt5) {

                                                            Commentaire C = new Commentaire();
                                                            String resC = new String(requeteCommentaire.getResponseData());

                                                            if (!(resC.equals("error"))) {
                                                                C.parse(resC);
                                                                C.setQuestion(QL);

                                                                ConnectionRequest rgetuserC = new ConnectionRequest();

                                                                rgetuserC.setUrl(cnxUser);
                                                                rgetuserC.setPost(false);
                                                                rgetuserC.addArgument("id", uidC);
                                                                NetworkManager.getInstance()
                                                                        .addToQueue(rgetuserC);
                                                                rgetuserC.addResponseListener(new ActionListener<NetworkEvent>() {
                                                                    @Override
                                                                    public void actionPerformed(NetworkEvent evt6) {
                                                                        User userC = new User();
                                                                        String resU2 = new String(rgetuserC.getResponseData());

                                                                        userC.parse(resU2);

                                                                        Label Score1 = new Label("            " + C.getScore());

                                                                        C.setUser(userC);

                                                                        Label Username1 = new Label(C.getUser().getLogin());

                                                                        try {

                                                                            Label profilC = new Label(Image.createImage("/usericon.png").scaled(300, 300));

                                                                            try {
                                                                                ec = EncodedImage.create("/load.jpg");
                                                                            } catch (IOException ex) {
                                                                                System.out.println(ex.getMessage());
                                                                            }
                                                                            if (!(C.getUser().getProfil().equals(""))) {
                                                                                profilC = new Label(URLImage.createToStorage(ec, "http://localhost/mobileshakehub/" + C.getUser().getProfil(), "http://localhost/mobileshakehub/" + C.getUser().getProfil(), URLImage.RESIZE_SCALE));
                                                                                profilC.setHeight(300);
                                                profilC.setWidth(300);
                                                                            } else {
                                                                                profilC = new Label(Image.createImage("/usericon.png").scaled(300, 300));
                                                                            }

                                                                            TextArea TC = new TextArea(C.getTexteCommentaire());
                                                                            TC.getAllStyles().setFgColor(BLACK);
                                                                            TC.getAllStyles().setBgColor(CBGBlue);
                                                                            if (C.getScore() < 0) {
                                                                                Score1.getAllStyles().setFgColor(darkBlue);
                                                                            } else if (C.getScore() > 0) {
                                                                                Score1.getAllStyles().setFgColor(darkGreen);
                                                                            }
                                                                            Button delete1 = new Button("Shake Out");
                                                                            Button upvote1 = new Button("Shake Up");
                                                                            upvote1.getAllStyles().setFgColor(darkYellow);

                                                                            Button downvote1 = new Button("Shake Down");
                                                                            downvote1.getAllStyles().setFgColor(darkBlue);
                                                                            int initC = C.getScore();
                                                                            upvote1.addLongPressListener(new ActionListener() {
                                                                                @Override
                                                                                public void actionPerformed(ActionEvent evtud) {

                                                                                    if (initC - C.getScore() == 0) {
                                                                                        C.setScore(C.getScore() + 1);
                                                                                        C.shake(C.getQuestion().getQuestionId(), C.getScore(), C.getUser().getUserId());

                                                                                        upvote1.getAllStyles().setFgColor(BLACK);
                                                                                        downvote1.getAllStyles().setFgColor(darkBlue);
                                                                                        downvote1.setEnabled(true);
                                                                                        upvote1.setEnabled(false);
                                                                                        Score1.setText("            " + C.getScore());

                                                                                    }

                                                                                }
                                                                            });

                                                                            downvote1.addLongPressListener(new ActionListener() {
                                                                                @Override
                                                                                public void actionPerformed(ActionEvent evtdv) {
                                                                                    if (initC - C.getScore() == 0) {
                                                                                        C.setScore(C.getScore() - 1);
                                                                                        C.shake(C.getQuestion().getQuestionId(), C.getScore(), C.getUser().getUserId());
                                                                                        downvote1.getAllStyles().setFgColor(BLACK);
                                                                                        upvote1.getAllStyles().setFgColor(darkYellow);
                                                                                        downvote1.setEnabled(false);
                                                                                        upvote1.setEnabled(true);
                                                                                        Score1.setText("            " + C.getScore());

                                                                                    }

                                                                                }
                                                                            });

                                                                            Label date1 = new Label(C.getDateCommentaire() + "");
                                                                            Container C6 = BoxLayout.encloseYCenter(upvote1, Score1, downvote1);
                                                                            Button BBan = new Button("Ban User");
                                                                            BBan.getAllStyles().setFgColor(BLACK);
                                                                            if ((U.getEmail().equals(C.getUser().getEmail())) || (U.getRole().equals("a:1:{i:0;s:5:\"ADMIN\";}"))) {
                                                                                TC.setEditable(true);
                                                                                delete1.getAllStyles().setFgColor(softRed);
                                                                                C6.add(delete1);
                                                                                if ((U.getRole().equals("a:1:{i:0;s:5:\"ADMIN\";}"))) {
                                                                                    C6.add(BBan);
                                                                                }
                                                                                if (U.getEmail().equals(C.getUser().getEmail())) {
                                                                                    Username1.getAllStyles().setFgColor(darkGreen);
                                                                                }
                                                                            } else {
                                                                                TC.setEditable(false);
                                                                            }
                                                                            Container CProfil = BoxLayout.encloseX(profilC, Username1);

                                                                            Label LC = new Label("Tap this when you're done to Reshake!");

                                                                            LC.getAllStyles()
                                                                                    .setFgColor(softGreen);
                                                                            Container C5 = BoxLayout.encloseXRight(C6, TC);
                                                                            Container C4 = BoxLayout.encloseYBottomLast(CProfil, date1, C5);

                                                                            TC.addDataChangedListener(new DataChangedListener() {

                                                                                @Override
                                                                                public void dataChanged(int type, int index) {
                                                                                    if ((!C4.contains(LC))) {
                                                                                        C4.add(LC);

                                                                                    }

                                                                                }

                                                                            }
                                                                            );
                                                                            LC.addPointerPressedListener(new ActionListener() {
                                                                                @Override
                                                                                public void actionPerformed(ActionEvent dc2) {

                                                                                    C4.removeComponent(LC);
                                                                                    C.update(C.getQuestion().getQuestionId(), TC.getText(), C.getUser().getUserId());
                                                                                    S.revalidate();

                                                                                }
                                                                            });
                                                                            C4.getStyle()
                                                                                    .setPadding(0, 0, 200, 0);
                                                                            C4.getAllStyles()
                                                                                    .setBgColor(CBGBlue);
                                                                            C1.getAllStyles()
                                                                                    .setBgColor(QBGYellow);

                                                                            C1.add(C4);
                                                                            delete1.addLongPressListener(new ActionListener() {
                                                                                @Override
                                                                                public void actionPerformed(ActionEvent evtud) {
                                                                                    C.delete(QL.getQuestionId(), userC.getUserId(), C.getTexteCommentaire());
                                                                                    C4.remove();
                                                                                }
                                                                            });
                                                                            BBan.addLongPressListener(new ActionListener() {
                                                                                @Override
                                                                                public void actionPerformed(ActionEvent evtud) {
                                                                                    QL.ban(QL.getUser());
                                                                                }
                                                                            });
                                                                            S.revalidate();

                                                                        } catch (IOException ex) {
                                                                            System.out.println(ex.getMessage());
                                                                        }
                                                                    }
                                                                }
                                                                );
                                                            }
                                                        }

                                                    });
                                                }
                                                j = res2.indexOf("Id Commentaire: ", j + 1);

                                            }
                                            Button BBanC = new Button("Ban User");
                                            BBanC.getAllStyles().setFgColor(BLACK);
                                            if ((U.getEmail()
                                                    .equals(QL.getUser().getEmail())) || (U.getRole().equals("a:1:{i:0;s:5:\"ADMIN\";}"))) {
                                                TQ.setEditable(true);
                                                delete.getAllStyles().setFgColor(softRed);
                                                BBanC.getAllStyles().setFgColor(BLACK);
                                                C3.add(delete);

                                                if ((U.getRole().equals("a:1:{i:0;s:5:\"ADMIN\";}"))) {
                                                    C3.add(BBanC);
                                                }
                                                if (U.getEmail()
                                                        .equals(QL.getUser().getEmail())) {
                                                    Username.getAllStyles().setFgColor(darkGreen);
                                                }

                                            } else {
                                                TQ.setEditable(false);
                                            }
                                            if (!C1.contains(Reply)) {
                                                C1.add(Reply);
                                            }
                                            if (!S.contains(C1)) {
                                                S.add(C1);
                                            }
                                            delete.addLongPressListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evtud) {
                                                    QL.delete(QL.getQuestionId(), user.getUserId());
                                                    C1.remove();
                                                }
                                            });
                                            BBanC.addLongPressListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evtud) {

                                                    QL.ban(QL.getUser());
                                                    C1.remove();
                                                }
                                            });
                                        } catch (IOException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                    }
                                }
                                );

                            }
                        }
                        );

                        i = res2.indexOf("Id Question: ", i + 1);
                    }

                }

            }
            );
        } else {
            Dialog.show("Banni du ShakeHub", (U.getPrenomUser() + " " + U.getNomUser() + ", votre compte a été suspendu du ShakeHub en raison de votre transgression des règles de celui-ci."), null, TYPE_INFO, null, 5000);
        }
        S.getToolbar()
                .addMaterialCommandToOverflowMenu("Submit Question", FontImage.MATERIAL_ADD_COMMENT, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent back
                    ) {
                        Form addQuestion = new Form("Submit a Question to ShakeHub", BoxLayout.y());
                        Label ur = new Label("Type your Question below: ");
                        TextArea TQS = new TextField("", "Respect the rules of the ShakeHub.");
                        TQS.getAllStyles().setFgColor(darkBlue);
                        Button SubmitQ = new Button("Submit");
                        SubmitQ.getAllStyles().setFgColor(darkYellow);
                        Button CancelQ = new Button("Cancel");
                        CancelQ.getAllStyles().setFgColor(darkBlue);

                        SubmitQ.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                ConnectionRequest requeteSubmitQ = new ConnectionRequest();

                                requeteSubmitQ.setUrl(cnxSubmitQ);
                                requeteSubmitQ.setPost(false);
                                requeteSubmitQ.addArgument("uid", U.getUserId() + "");
                                String SX = StringUtil.replaceAll(TQS.getText(), "'", "`");
                                requeteSubmitQ.addArgument("tq", SX);
                                Date DQ = new Date(System.currentTimeMillis());
                                String xdQ = new SimpleDateFormat().format(DQ);

                                xdQ = (("20" + xdQ.substring(xdQ.lastIndexOf("/"), xdQ.lastIndexOf("/", xdQ.indexOf(" ")))))
                                        + "/"
                                        + ((xdQ.substring(0, xdQ.indexOf("/", 0)).length() == 1) ? "0" + xdQ.substring(0, xdQ.indexOf("/", 0)) : xdQ.substring(0, xdQ.indexOf("/", 0)))
                                        + "/"
                                        + ((xdQ.substring(xdQ.indexOf("/", 0), xdQ.indexOf("/", xdQ.indexOf("/", 2))).length() == 1) ? "0" + xdQ.substring(xdQ.indexOf("/", 0), xdQ.indexOf("/", 2)) : xdQ.substring(xdQ.indexOf("/", 0), xdQ.indexOf("/", 2)));
                                requeteSubmitQ.addArgument("date", xdQ);

                                NetworkManager.getInstance()
                                        .addToQueue(requeteSubmitQ);
                                requeteSubmitQ.addResponseListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent evts) {
                                        Dialog.show("Success!", ("Thank you for submitting a Question on ShakeHub " + U.getPrenomUser() + " " + U.getNomUser() + " !"), null, TYPE_INFO, null, 2000);
                                        Label UsernameQ = new Label(U.getLogin());
                                        try {
                                            Label profilQ;
                                            try {
                                                ec = EncodedImage.create("/load.jpg");
                                            } catch (IOException ex) {
                                                System.out.println(ex.getMessage());
                                            }
                                            if (!(U.getProfil().equals(null))) {
                                                profilQ = new Label(URLImage.createToStorage(ec, "http://localhost/mobileshakehub/" + U.getProfil(), "http://localhost/mobileshakehub/" + U.getProfil(), URLImage.RESIZE_SCALE));
                                                profilQ.setHeight(300);
                                                profilQ.setWidth(300);
                                            } else {
                                                profilQ = new Label(Image.createImage("/usericon.png").scaled(300, 300));
                                            }
                                            profilQ.getAllStyles().setPadding(0, 0, 0, 0);
                                            TextArea TQ = new TextArea(TQS.getText());
                                            TQ.getAllStyles().setFgColor(darkBlue);
                                            TQ.getAllStyles().setBgColor(darkYellow);
                                            Label Score = new Label("             0");

                                            Button upvote = new Button("Shake Up");

                                            upvote.getAllStyles().setFgColor(softOrange);

                                            Button downvote = new Button("Shake Down");
                                            downvote.getAllStyles().setFgColor(softBlue);

                                            Label date = new Label(DQ + "");

                                            Container CQ3 = BoxLayout.encloseYCenter(upvote, Score, downvote);
                                            Container CQ2 = BoxLayout.encloseX(CQ3, TQ);
                                            Container CQProfil = BoxLayout.encloseX(profilQ, UsernameQ);
                                            Container CQ1 = BoxLayout.encloseY(CQProfil, date, CQ2);

                                            S.add(CQ1);

                                        } catch (IOException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                        S.showBack();
                                    }

                                });

                            }
                        }
                        );
                        CancelQ.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evtscs) {
                                addQuestion.removeAll();
                                S.showBack();
                            }
                        });
                        addQuestion.addAll(ur, TQS, SubmitQ, CancelQ);

                        addQuestion.show();
                    }
                }
                );
        S.getToolbar()
                .addMaterialCommandToOverflowMenu("My Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent back
                    ) {
                        Form profile = new Form("Your Profile", BoxLayout.y());

                        try {
                            Label nomprenom = new Label("Your name: " + U.getPrenomUser() + " " + U.getNomUser());
                            Label mailaddress = new Label("Your E-Mail Address: " + U.getEmail());
                            Label loginp = new Label("Login: " + U.getLogin());
                            Label numtel = new Label("Téléphone: " + U.getTelephone());
                            Label adresse = new Label("Adresse: " + U.getRue() + ", " + U.getVille() + ", " + U.getPays());
                            Label iv;

                            if (!(U.getProfil().equals(""))) {
                                iv = new Label(Image.createImage(U.getProfil()).scaled(300, 300));
                            } else {
                                iv = new Label(Image.createImage("/usericon.png").scaled(300, 300));
                            }
                            profile.addAll(iv, nomprenom, mailaddress, loginp, numtel, adresse);

                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        profile.getToolbar()
                                .addMaterialCommandToOverflowMenu("Back to ShakeHub", FontImage.MATERIAL_BACKSPACE, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent back
                                    ) {
                                        profile.removeAll();
                                        S.showBack();

                                    }
                                }
                                );
                        profile.show();
                    }
                });
        S.getToolbar()
                .addMaterialCommandToOverflowMenu("Sign out", FontImage.MATERIAL_BACKSPACE, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent back
                    ) {
                        S.removeAll();
                        new MenuPrincipal();

                    }
                }
                );
        S.show();
    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }
}
