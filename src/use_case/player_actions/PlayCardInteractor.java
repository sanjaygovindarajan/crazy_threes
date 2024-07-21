package use_case.player_actions;

import entity.*;
import interface_adapter.StartGameOutputBoundary;
import interface_adapter.StartGameOutputData;
import interface_adapter.StartGamePresenter;

public class PlayCardInteractor implements PlayCardInputBoundary {
    GameInterface game;
    StartGameOutputBoundary presenter;

    public PlayCardInteractor(StartGameOutputBoundary presenter){
        this.presenter = presenter;
    }

    @Override
    public void playCard(int number, char suit){
        Player player = game.getCurrentPlayer();
        boolean threeCase = (number == 3);
        int index = -1;
        int count = 0;
        for(Card card: player.viewHand().getCardList()){
            if(card.getCardNum() == number && card.getDisplaySuit() == suit){
                index = count;
            }
            count++;
        }
        if(index == -1){
            presenter.loadMissingCardView();
            return;
        }
        try {
            if (!threeCase) {
                game.playCard(player, index);
            } else {
                presenter.loadThreeView(suit);
            }
            player = game.getCurrentPlayer();
            StartGameOutputData outputData = new StartGameOutputData(
                    player.viewHand().toString(),
                    player.getName(),
                    game.getDiscard().getCard().toString(),
                    game.getDiscard().getSuit());
            presenter.loadSuccessView(outputData);
        } catch (InvalidCardException e) {
            presenter.loadInvalidCardView();
        }
    }
    public void playThree(char suit, char newSuit){
        int index = -1;
        int count = 0;
        for(Card card: game.getCurrentPlayer().viewHand().getCardList()){
            if(card.getCardNum() == 3 && card.getDisplaySuit() == suit){
                index = count;
            }
            count++;
        }
        if(index == -1) {
            presenter.loadInvalidCardView();
            return;
        }
        try {
            game.playThree(index, newSuit);
        }
        catch(InvalidCardException e){
            presenter.loadInvalidCardView();
        }
    }

    @Override
    public StartGameOutputBoundary getPresenter() {
        return presenter;
    }

    @Override
    public void setGame(GameInterface game) {
        this.game = game;
    }
}

// load request suit view
