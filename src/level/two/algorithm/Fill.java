package level.two.algorithm;

public class Fill {
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor == newColor) {
            return image; // 새 색상이 원래 색상과 같으면 채울 필요갸 없음
        }

        fill(image, sr, sc, originalColor, newColor);
        return image;
    }

    private static void fill(int[][] image, int row, int col, int originalColor, int newColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != originalColor) {
            return;
        }

        image[row][col] = newColor;

        fill(image, row + 1, col, originalColor, newColor); // 아래
        fill(image, row - 1, col, originalColor, newColor); // 위
        fill(image, row, col + 1, originalColor, newColor); // 오른쪽
        fill(image, row, col - 1, originalColor, newColor); // 왼쪽
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1; // 시작 행
        int sc = 1; // 시작 열
        int newColor = 2; // 새 색상

        int[][] filledImage = floodFill(image, sr, sc, newColor);

        // 수정된 이미지 표시
        for (int[] row : filledImage) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
