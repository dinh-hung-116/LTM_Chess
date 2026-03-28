package com.chess.database.Class;

import java.time.LocalDateTime;

// LỚP NÀY DÙNG ĐỂ LƯU TRỮ THÔNG TIN CỦA MỘT VÁN ĐẤU
public class ChessMatch {
    private int matchID;
    private int whitePalyerID; // mã định danh của user trắng và đen trong một ván đấu
    private int blackPlayerID;
    private String result; // kết quả ván đấu. Kết quả theo định dạng:
    // 1-0(white>black), 0-1(white<black), 1-1(white=black)
    private LocalDateTime startTime; // thời gian bắt đầu và kết thúc của trận đấu(ngày + thời gian). Mỗi ván 10’
    private LocalDateTime endTime;

    public ChessMatch(int matchID, int whitePalyerID, int blackPlayerID, String result, LocalDateTime startTime, LocalDateTime endTime) {
        this.matchID = matchID;
        this.whitePalyerID = whitePalyerID;
        this.blackPlayerID = blackPlayerID;
        this.result = result;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ChessMatch() {
    }

    // GETTER

    public int getMatchID() {
        return matchID;
    }

    public int getWhitePalyerID() {
        return whitePalyerID;
    }

    public int getBlackPlayerID() {
        return blackPlayerID;
    }

    public String getResult() {
        return result;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    // SETTER

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setWhitePalyerID(int whitePalyerID) {
        this.whitePalyerID = whitePalyerID;
    }

    public void setBlackPlayerID(int blackPlayerID) {
        this.blackPlayerID = blackPlayerID;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    

    @Override
    public String toString() {
        return "Match{" + "matchID=" + matchID + ", whitePalyerID=" + whitePalyerID + ", blackPlayerID=" + blackPlayerID + ", result=" + result + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
    
}
