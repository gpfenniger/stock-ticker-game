package game.board;

import game.enums.Stock;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** 	Holds all static methods for drawing the board
	@author Griffin Pfenniger < griffin (dot) pfenniger (at) gmail >
	@version 0.0, 2018-01-13
*/
public class Board {
    private static GraphicsContext gc;

    private static final int COL_HEIGHT = 30;
    private static final int ROW_WIDTH = 500 / 21;
    
    public static void setContext(GraphicsContext newGC) { gc = newGC; }

    public static void draw() {
        gc.clearRect(0, 0, 500, 200);
        drawBorder();
        for (int i = 0; i < 6; i++)
            drawColumn(i);
        for (int i = 0; i < 20; i++)
            drawRow(i);
        drawTokens();
    }

    private static void drawBorder() {
        gc.beginPath();
        gc.moveTo(0, 0);
        gc.lineTo(500, 0);
        gc.lineTo(500, 180);
        gc.lineTo(0, 180);
        gc.lineTo(0, 0);
        gc.stroke();
    }

    private static void drawColumn(int col) {
        gc.beginPath();
        gc.setFill(Stock.stocks[col].getColour());
        gc.fillRect(0, col * COL_HEIGHT, 500, COL_HEIGHT);
        gc.moveTo(0, col * COL_HEIGHT);
        gc.lineTo(500, col * COL_HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillText("" + Stock.stocks[col].getValue(), 460, ((col + 1) * COL_HEIGHT) - 10);
        gc.stroke();
    }

    private static void drawRow(int row) {
        gc.beginPath();
        gc.moveTo(row * ROW_WIDTH, 0);
        gc.lineTo(row * ROW_WIDTH, 180);
        gc.stroke();
    }

    private static void drawTokens() {
        gc.setFill(Color.BLACK);
        for (int i = 0; i < 6; i++)
            gc.fillRect((Stock.stocks[i].getValue() / 10) * ROW_WIDTH, i * COL_HEIGHT, ROW_WIDTH, COL_HEIGHT);
    }
}