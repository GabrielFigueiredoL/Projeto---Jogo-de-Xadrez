package secao16.aplication;

import secao16.chessLayer.model.entities.ChessMatch;
import secao16.chessLayer.model.entities.ChessPiece;
import secao16.chessLayer.model.entities.ChessPosition;
import secao16.chessLayer.model.exceptions.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);
        List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, capturedPieces);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null) {
                    capturedPieces.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q: ");
                    String chessPieceType = sc.nextLine().toUpperCase();
                    while (!chessPieceType.equals("B") && !chessPieceType.equals("N") && !chessPieceType.equals("R") && !chessPieceType.equals("Q")) {
                        System.out.print("Ivalid value. Enter piece for promotion (B/N/R/Q: ");
                        chessPieceType = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(chessPieceType);
                }
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, capturedPieces);
    }
}