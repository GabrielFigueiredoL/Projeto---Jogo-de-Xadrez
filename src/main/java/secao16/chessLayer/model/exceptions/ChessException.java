package secao16.chessLayer.model.exceptions;

import secao16.boardLayer.model.exceptions.BoardException;

public class ChessException extends BoardException {
    private static final long serialVersionUID = 1L;

    public ChessException(String msg) {
        super(msg);
    }
}
