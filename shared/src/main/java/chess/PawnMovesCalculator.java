package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMovesCalculator implements ChessMovesCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(position);
        ChessGame.TeamColor color = piece.getTeamColor();
        int row = position.getRow();
        int col = position.getColumn();

        int direction = (color == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int startRow = (color == ChessGame.TeamColor.WHITE) ? 2 : 7;
        int promotionRow = (color == ChessGame.TeamColor.WHITE) ? 8 : 1;

        int newRow = row + direction;

        //Forward, optional double start, forward promotion
        if (onBoard(newRow, col) && isEmpty(board, newRow, col)) {
            ChessPosition newPosition = new ChessPosition(newRow, col);
            if (newRow == promotionRow) {
                ChessPosition promotionPosition = new ChessPosition(newRow, col);
                addValidPromotionMoves(validMoves, position, promotionPosition);
            } else if (isEmpty(board, newRow, col)) {
                addValidMoves(validMoves, position, newPosition);
                if (isEmpty(board, row + direction * 2, col) && row == startRow) {
                    ChessPosition doublePosition = new ChessPosition(row + direction * 2, col);
                    addValidMoves(validMoves, position, doublePosition);
                }
            }
        }

        //Diagonal capture, diagonal promotion capture
        int[] colMoves = {-1, 1};
        for (int i = 0; i < 2; i++) {
            int newCol = col + colMoves[i];
            if (onBoard(newRow, newCol)) {
                ChessPosition capturePosition = new ChessPosition(newRow, newCol);
                ChessPiece capturePiece = board.getPiece(capturePosition);
                if (capturePiece != null && capturePiece.getTeamColor() != color) {
                    if (newRow == promotionRow) {
                        addValidPromotionMoves(validMoves, position, capturePosition);
                    } else {
                        addValidMoves(validMoves, position, capturePosition);
                    }
                }
            }
        }
        return validMoves;
    }
    private void addValidPromotionMoves(Collection<ChessMove> validMoves,  ChessPosition start, ChessPosition end) {
        ChessMove queen = new ChessMove(start, end, ChessPiece.PieceType.QUEEN);
        ChessMove rook = new ChessMove(start, end, ChessPiece.PieceType.ROOK);
        ChessMove bishop = new ChessMove(start, end, ChessPiece.PieceType.BISHOP);
        ChessMove knight = new ChessMove(start, end, ChessPiece.PieceType.KNIGHT);
        validMoves.add(queen);
        validMoves.add(rook);
        validMoves.add(bishop);
        validMoves.add(knight);
    }
    private void addValidMoves(Collection<ChessMove> validMoves, ChessPosition start, ChessPosition end) {
        validMoves.add(new ChessMove(start, end, null));
    }

    private boolean isEmpty(ChessBoard board, int row, int col) {
        if (onBoard(row, col)) {
            ChessPosition position = new ChessPosition(row, col);
            return board.getPiece(position) == null;
        }
        return false;
    }
    private boolean onBoard(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }
}
