package secao16.chessLayer.model.entities.chessPieces;

import secao16.boardLayer.model.entities.Board;
import secao16.boardLayer.model.entities.Position;
import secao16.chessLayer.model.entities.ChessMatch;
import secao16.chessLayer.model.entities.ChessPiece;
import secao16.chessLayer.model.enums.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0, 0);

        // up
        auxPosition.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // up-right
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // right
        auxPosition.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // right-down
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // down
        auxPosition.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // down-left
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // left
        auxPosition.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // left-up
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //special move - castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            //special move - kingside rook
            Position positionRookKingSide = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(positionRookKingSide)) {
                Position auxPosition1 = new Position(position.getRow(), position.getColumn() + 1);
                Position auxPosition2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(auxPosition1) == null && getBoard().piece(auxPosition2) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            //special move - queenside rook
            Position positionRookQueenSide = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(positionRookQueenSide)) {
                Position auxPosition1 = new Position(position.getRow(), position.getColumn() - 1);
                Position auxPosition2 = new Position(position.getRow(), position.getColumn() - 2);
                Position auxPosition3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(auxPosition1) == null && getBoard().piece(auxPosition2) == null && getBoard().piece(auxPosition3) == null) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return mat;
    }
}
