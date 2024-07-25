package secao16.chessLayer.model.entities.chessPieces;

import secao16.boardLayer.model.entities.Board;
import secao16.boardLayer.model.entities.Position;
import secao16.chessLayer.model.entities.ChessPiece;
import secao16.chessLayer.model.enums.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            //normal move
            auxPosition.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //first move
            auxPosition.setValues(position.getRow() - 2, position.getColumn());
            Position firstPosition = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(auxPosition)
                    && !getBoard().thereIsAPiece(auxPosition)
                    && !getBoard().thereIsAPiece(firstPosition)
                    && getBoard().positionExists(auxPosition)
                    && getMoveCount() == 0) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //capture move left
            auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //capture move right
            auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }
        }

        if (getColor() == Color.BLACK) {
            //normal move
            auxPosition.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //first move
            auxPosition.setValues(position.getRow() + 2, position.getColumn());
            Position firstPosition = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(auxPosition)
                    && !getBoard().thereIsAPiece(auxPosition)
                    && !getBoard().thereIsAPiece(firstPosition)
                    && getBoard().positionExists(auxPosition)
                    && getMoveCount() == 0) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //capture move left
            auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }

            //capture move right
            auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
                mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
            }
        }

        return mat;
    }
}
