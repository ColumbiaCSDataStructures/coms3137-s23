import java.util.List; 
import java.util.ArrayList;
import java.util.LinkedList;

public class Matrix<T> {

  int height;
  int width;

  List<List<T>> data; 

  public Matrix(int height, int width, T[] rawElements) {
    this.height = height;
    this.width = width; 
    data = new ArrayList<List<T>>(); 

    for (int i = 0; i < height; i++) {
        
      List<T> row = new ArrayList<T>();
      for (int j = 0; j < width; j++) {
          row.add(rawElements[width * i + j]); 
      }
      data.add(row);     
    }
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    for (List<T> row : data) {
      for (T element : row) {
        result.append(element.toString());
        result.append(" ");
      }
      result.append("\n");
    }
    return result.toString();
  }


  public void transpose() {
    
    List<List<T>> newData = new ArrayList<>();

    for (int j = 0; j < width; j++){
      ArrayList<T> newRow = new ArrayList<>();     
      for (int i = 0; i < height; i++) {
        newRow.add(data.get(i).get(j));
      }
      newData.add(newRow);
    }

    data = newData; 
    int tmp = height; 
    height = width; 
    width = tmp;

  }


  public static void main(String[] args) {
   
    Integer[] rawData =  {5,3,0,7,9,8,3,1,4,2,1,6}; 
    Matrix<Integer> m = new Matrix<>(3, 4, rawData); 

    System.out.println(m);
    m.transpose(); 
    System.out.println(m); // should print the transpose of m
    

  }

}
