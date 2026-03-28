package com.chess.database.Class;

// LỚP NÀY DÙNG ĐỂ CHỨA CÁC THÔNG TIN VỀ THÀNH TÍCH CỦA NGƯỜI DÙNG
public class UserStats {
    private int userID; // khóa ngoại trỏ đến userID của User
    private int totalGames;
    private int wins; // tổng số trận thắng, thua và hòa
    private int losses;
    private int draws;
    private Double winRate; // tỉ lệ thắng
    private int winningStreak; // chuỗi thắng và thua dài nhất
    private int losingStreak;
    private int elo; // điểm thành tích tự tính

    public UserStats(int userID, int totlalGames, int wins, int losses, int draws, Double winRate, int winningStreak, int losingStreak, int elo) {
        this.userID = userID;
        this.totalGames = totalGames;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.winRate = winRate;
        this.winningStreak = winningStreak;
        this.losingStreak = losingStreak;
        this.elo = elo;
    }

    // GETTER
    public int getUserID() {
        return userID;
    }
    
    public int getTotalGames() {
        return totalGames;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public Double getWinRate() {
        return winRate;
    }

    public int getWinningStreak() {
        return winningStreak;
    }

    public int getLosingStreak() {
        return losingStreak;
    }

    public int getElo() {
        return elo;
    }
    
    // SETTER

    public void setUserID(int userID) {
        this.userID = userID;
    }
        
    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }

    public void setWinningStreak(int winningStreak) {
        this.winningStreak = winningStreak;
    }

    public void setLosingStreak(int losingStreak) {
        this.losingStreak = losingStreak;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
    
    @Override
    public String toString() {
        return "UserStats{" + "userID=" + userID + ", totalGames=" + totalGames +", wins=" + wins + ", losses=" + losses + ", draws=" + draws + ", winRate=" + winRate + ", winningStreak=" + winningStreak + ", losingStreak=" + losingStreak + ", elo=" + elo + '}';
    }
    
}
