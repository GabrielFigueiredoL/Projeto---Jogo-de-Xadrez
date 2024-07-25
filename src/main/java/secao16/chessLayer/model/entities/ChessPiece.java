package secao16.chessLayer.model.entities;

import secao16.boardLayer.model.entities.Board;
import secao16.boardLayer.model.entities.Piece;
import secao16.boardLayer.model.entities.Position;
import secao16.chessLayer.model.enums.Color;

public abstract class ChessPiece extends Piece {
    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }
}
