package RPG;

import javax.swing.JPanel;

public class MenuView extends javax.swing.JPanel {

    /**
     * Creates new form MenuView2
     */
    public MenuView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        savesButtonGroup = new javax.swing.ButtonGroup();
        menuPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        bestScoreTitleLabel = new javax.swing.JLabel();
        bestScoreLabel = new javax.swing.JLabel();
        newGameButton = new javax.swing.JButton();
        loadGameButton = new javax.swing.JButton();
        viewScoresButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        viewScoresPanel = new javax.swing.JPanel();
        scoresTitleLabel = new javax.swing.JLabel();
        scoresBackButton = new javax.swing.JButton();
        scoresScrollPane = new javax.swing.JScrollPane();
        scoresTextArea = new javax.swing.JTextArea();
        scoresPanelTitleLabel = new javax.swing.JLabel();
        loadGamePanel = new javax.swing.JPanel();
        loadGameTitleLabel = new javax.swing.JLabel();
        loadButton = new javax.swing.JButton();
        savesScrollPane = new javax.swing.JScrollPane();
        savesPanel = new javax.swing.JPanel();
        savesTitleLabel = new javax.swing.JLabel();
        loadGameBackButton = new javax.swing.JButton();
        newGamePanel = new javax.swing.JPanel();
        newGameTitleLabel = new javax.swing.JLabel();
        beginGameButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        newGameBackButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        gamePanel = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        menuPanel.setBackground(new java.awt.Color(102, 102, 102));

        titleLabel.setFont(new java.awt.Font("Candara Light", 1, 48)); // NOI18N
        titleLabel.setText("Into the Depths");

        bestScoreTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bestScoreTitleLabel.setText("Best Score");

        bestScoreLabel.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        bestScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        bestScoreLabel.setText("No scores yet!");

        newGameButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        newGameButton.setText("New Game");

        loadGameButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loadGameButton.setText("Load Game");

        viewScoresButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viewScoresButton.setText("View Scores");

        quitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        quitButton.setText("Quit");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewScoresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bestScoreLabel)
                    .addComponent(bestScoreTitleLabel)
                    .addComponent(titleLabel))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(titleLabel)
                .addGap(28, 28, 28)
                .addComponent(bestScoreTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bestScoreLabel)
                .addGap(46, 46, 46)
                .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loadGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewScoresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
        );

        add(menuPanel, "card2");

        viewScoresPanel.setBackground(new java.awt.Color(102, 102, 102));

        scoresTitleLabel.setFont(new java.awt.Font("Candara Light", 1, 48)); // NOI18N
        scoresTitleLabel.setText("Into the Depths");

        scoresBackButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        scoresBackButton.setText("Back");

        scoresScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scoresTextArea.setEditable(false);
        scoresTextArea.setBackground(new java.awt.Color(255, 255, 255));
        scoresTextArea.setColumns(20);
        scoresTextArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        scoresTextArea.setRows(5);
        scoresTextArea.setMargin(new java.awt.Insets(5, 10, 2, 6));
        scoresScrollPane.setViewportView(scoresTextArea);

        scoresPanelTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        scoresPanelTitleLabel.setText("Scores");

        javax.swing.GroupLayout viewScoresPanelLayout = new javax.swing.GroupLayout(viewScoresPanel);
        viewScoresPanel.setLayout(viewScoresPanelLayout);
        viewScoresPanelLayout.setHorizontalGroup(
            viewScoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewScoresPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(viewScoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(scoresTitleLabel)
                    .addComponent(scoresScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoresBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoresPanelTitleLabel))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        viewScoresPanelLayout.setVerticalGroup(
            viewScoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewScoresPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(scoresTitleLabel)
                .addGap(26, 26, 26)
                .addComponent(scoresPanelTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scoresScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(scoresBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        add(viewScoresPanel, "card2");

        loadGamePanel.setBackground(new java.awt.Color(102, 102, 102));

        loadGameTitleLabel.setFont(new java.awt.Font("Candara Light", 1, 48)); // NOI18N
        loadGameTitleLabel.setText("Into the Depths");

        loadButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loadButton.setText("Load");
        loadButton.setEnabled(false);

        savesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        savesPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout savesPanelLayout = new javax.swing.GroupLayout(savesPanel);
        savesPanel.setLayout(savesPanelLayout);
        savesPanelLayout.setHorizontalGroup(
            savesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        savesPanelLayout.setVerticalGroup(
            savesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        savesScrollPane.setViewportView(savesPanel);

        savesTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        savesTitleLabel.setText("Load Game");

        loadGameBackButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loadGameBackButton.setText("Back");

        javax.swing.GroupLayout loadGamePanelLayout = new javax.swing.GroupLayout(loadGamePanel);
        loadGamePanel.setLayout(loadGamePanelLayout);
        loadGamePanelLayout.setHorizontalGroup(
            loadGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadGamePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(loadGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(loadGameTitleLabel)
                    .addComponent(savesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(savesTitleLabel)
                    .addComponent(loadGameBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        loadGamePanelLayout.setVerticalGroup(
            loadGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadGamePanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(loadGameTitleLabel)
                .addGap(26, 26, 26)
                .addComponent(savesTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(savesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(loadGameBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        add(loadGamePanel, "card2");

        newGamePanel.setBackground(new java.awt.Color(102, 102, 102));

        newGameTitleLabel.setFont(new java.awt.Font("Candara Light", 1, 48)); // NOI18N
        newGameTitleLabel.setText("Into the Depths");

        beginGameButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        beginGameButton.setText("Begin Game");
        beginGameButton.setEnabled(false);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nameLabel.setText("Enter your name");

        newGameBackButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        newGameBackButton.setText("Back");

        nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout newGamePanelLayout = new javax.swing.GroupLayout(newGamePanel);
        newGamePanel.setLayout(newGamePanelLayout);
        newGamePanelLayout.setHorizontalGroup(
            newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(newGameTitleLabel)
                    .addComponent(beginGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(newGameBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        newGamePanelLayout.setVerticalGroup(
            newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(newGameTitleLabel)
                .addGap(26, 26, 26)
                .addComponent(nameLabel)
                .addGap(18, 18, 18)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                .addComponent(beginGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(newGameBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        add(newGamePanel, "card2");

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        add(gamePanel, "card6");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginGameButton;
    private javax.swing.JLabel bestScoreLabel;
    private javax.swing.JLabel bestScoreTitleLabel;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton loadGameBackButton;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JPanel loadGamePanel;
    private javax.swing.JLabel loadGameTitleLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newGameBackButton;
    private javax.swing.JButton newGameButton;
    private javax.swing.JPanel newGamePanel;
    private javax.swing.JLabel newGameTitleLabel;
    private javax.swing.JButton quitButton;
    private javax.swing.ButtonGroup savesButtonGroup;
    private javax.swing.JPanel savesPanel;
    private javax.swing.JScrollPane savesScrollPane;
    private javax.swing.JLabel savesTitleLabel;
    private javax.swing.JButton scoresBackButton;
    private javax.swing.JLabel scoresPanelTitleLabel;
    private javax.swing.JScrollPane scoresScrollPane;
    private javax.swing.JTextArea scoresTextArea;
    private javax.swing.JLabel scoresTitleLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton viewScoresButton;
    private javax.swing.JPanel viewScoresPanel;
    // End of variables declaration//GEN-END:variables

    //Return the menu panel
    public javax.swing.JPanel getMenuPanel() {
        return menuPanel;
    }

    //Return the begin game button
    public javax.swing.JButton getBeginGameButton() {
        return beginGameButton;
    }

    //Return the best score label
    public javax.swing.JLabel getBestScoreLabel() {
        return bestScoreLabel;
    }
    
    //Return the game panel
    public javax.swing.JPanel getGamePanel() {
        return gamePanel;
    }

    //Return the load game's load button
    public javax.swing.JButton getLoadButton() {
        return loadButton;
    }

    //Return the load game panel's back button
    public javax.swing.JButton getLoadGameBackButton() {
        return loadGameBackButton;
    }

    //Return the load game button
    public javax.swing.JButton getLoadGameButton() {
        return loadGameButton;
    }

    //Return the load game panel
    public javax.swing.JPanel getLoadGamePanel() {
        return loadGamePanel;
    }

    //Return the name text field
    public javax.swing.JTextField getNameTextField() {
        return nameTextField;
    }

    //Return the new game panel's back button
    public javax.swing.JButton getNewGameBackButton() {
        return newGameBackButton;
    }

    //Return the new game button
    public javax.swing.JButton getNewGameButton() {
        return newGameButton;
    }

    //Return the new game panel
    public javax.swing.JPanel getNewGamePanel() {
        return newGamePanel;
    }

    //Return the quit button
    public javax.swing.JButton getQuitButton() {
        return quitButton;
    }

    //Return the saves scroll pane
    public javax.swing.JScrollPane getSavesScrollPane() {
        return savesScrollPane;
    }
    
    //Return the saves panel
    public javax.swing.JPanel getSavesPanel() {
        return savesPanel;
    }

    //Return the saves button group
    public javax.swing.ButtonGroup getSavesButtonGroup() {
        return savesButtonGroup;
    }

    //Return the score panel's back button
    public javax.swing.JButton getScoresBackButton() {
        return scoresBackButton;
    }
    
    //Return scores text area
    public javax.swing.JTextArea getScoresTextArea() {
        return scoresTextArea;
    }

    //Return the view scores button
    public javax.swing.JButton getViewScoresButton() {
        return viewScoresButton;
    }

    //Return the view scores panel
    public javax.swing.JPanel getViewScoresPanel() {
        return viewScoresPanel;
    }
    
    //Return the game panel
    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    //Events the MenuController must listen for
    public void addController(MenuController controller) {
        this.getNewGameButton().addActionListener(controller);
        this.getLoadGameButton().addActionListener(controller);
        this.getViewScoresButton().addActionListener(controller);
        this.getQuitButton().addActionListener(controller);
        
        this.getLoadButton().addActionListener(controller);
        
        this.getBeginGameButton().addActionListener(controller);
        getNameTextField().addKeyListener(controller);
        
        this.getNewGameBackButton().addActionListener(controller);
        this.getLoadGameBackButton().addActionListener(controller);
        this.getScoresBackButton().addActionListener(controller);
    }
}
