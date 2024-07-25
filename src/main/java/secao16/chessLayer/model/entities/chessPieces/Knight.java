package secao16.chessLayer.model.entities.chessPieces;

import secao16.boardLayer.model.entities.Board;
import secao16.boardLayer.model.entities.Position;
import secao16.chessLayer.model.entities.ChessPiece;
import secao16.chessLayer.model.enums.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece)getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0 ,0);

        //up-right
        auxPosition.setValues(position.getRow() - 2, position.getColumn() + 1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //right-up
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 2);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //right-down
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 2);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //down-right
        auxPosition.setValues(position.getRow() + 2, position.getColumn() + 1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //down-left
        auxPosition.setValues(position.getRow() + 2, position.getColumn() - 1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //left-down
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 2);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //left-up
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 2);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        //up-left
        auxPosition.setValues(position.getRow() - 2, position.getColumn() - 1);
        if(getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        return mat;
    }
}
