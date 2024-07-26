package secao16.chessLayer.model.entities.chessPieces;

import secao16.boardLayer.model.entities.Board;
import secao16.boardLayer.model.entities.Position;
import secao16.chessLayer.model.entities.ChessMatch;
import secao16.chessLayer.model.entities.ChessPiece;
import secao16.chessLayer.model.enums.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

            // special move - white en passant
            if (position.getRow() == 3) {
                Position leftPiecePosition = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(leftPiecePosition)
                        && isThereOpponentPiece(leftPiecePosition)
                        && getBoard().piece(leftPiecePosition) == chessMatch.getEnPassantVulnerable()) {
                    mat[leftPiecePosition.getRow() - 1][leftPiecePosition.getColumn()] = true;
                }

                Position rightPiecePosition = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(rightPiecePosition)
                        && isThereOpponentPiece(rightPiecePosition)
                        && getBoard().piece(rightPiecePosition) == chessMatch.getEnPassantVulnerable()) {
                    mat[rightPiecePosition.getRow() - 1][rightPiecePosition.getColumn()] = true;
                }
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

            // special move - black en passant
            if (position.getRow() == 4) {
                Position leftPiecePosition = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(leftPiecePosition)
                        && isThereOpponentPiece(leftPiecePosition)
                        && getBoard().piece(leftPiecePosition) == chessMatch.getEnPassantVulnerable()) {
                    mat[leftPiecePosition.getRow() + 1][leftPiecePosition.getColumn()] = true;
                }

                Position rightPiecePosition = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(rightPiecePosition)
                        && isThereOpponentPiece(rightPiecePosition)
                        && getBoard().piece(rightPiecePosition) == chessMatch.getEnPassantVulnerable()) {
                    mat[rightPiecePosition.getRow() + 1][rightPiecePosition.getColumn()] = true;
                }
            }
        }

        return mat;
    }
}
