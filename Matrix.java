import java.util.Scanner;
import java.lang.Math;
public class Matrix {
    public int n, m;
    public double[][] main_matrix=new double[n][m];

    public void create_empty() {            //Метод, що створює порожню матрицю розміром n на m
        double[][] matri= new double[n][m];
        main_matrix=matri;
    }

    public void create_fixed(int n1, int m1) {   //Метод, що створює матрицю фіксованого розміру n1*m1
        n=n1;
        m=m1;
        double[][] matrix = new double[n1][m1];
        main_matrix=matrix;
    }

    public int[][] create_copy(int[][] matrix) { // Метод, що створює копію іншої матриці
        int[][] copy = matrix;
        return matrix;
    }

    public void fill_matrix() {                       // Метод, що дозволяє користувачу
        Scanner t=new Scanner(System.in);                       // заповнити матрицю значеннями "вручну"
        System.out.println("------------------\nУведіть елементи матриці:");
        for(int i=0; i<n; i++){
            for(int j=0; j<m;j++){
                System.out.println("Уведіть елемент з індексами ["+i+"]["+j+"]");
                main_matrix[i][j]=t.nextInt();
            }
        }

    }

    public void show_matrix() {   //метод, що виводить на екран матрицю
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(main_matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
    public void show_matrix_2(double[][] matrix, int n, int m) {   //метод, що виводить на екран матрицю
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%.2f",matrix[i][j]);
                System.out.print(" ");

            }
            System.out.println();
        }

    }
    public void fill_matrix_random() {
        // Метод, що дозволяє користувачу
        // заповнити матрицю випадковими значеннями від -500 до 500
        for(int i=0; i<n; i++){
            for(int j=0; j<m;j++){
                main_matrix[i][j]=(int) (Math.random()*1001) -500;
            }
        }
    }
    double get_value(int i, int j){
        return main_matrix[i][j];
    }
    double[] get_line(int i){
        return main_matrix[i];
    }
    void show_array(int[] arr, int len){
        for(int i=0; i<len; i++){
            System.out.print(arr[i]+" ");
        }
    }
    double[] get_column(int j1){
        double mat[]=new double[n];
        for(int i=0;i<n; i++){
            mat[i]=main_matrix[i][j1];
        }
        return mat;
    }
    int[] get_size(){
        int[] size={main_matrix.length, main_matrix[0].length};
        return size;
    }
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o==null || getClass()!=o.getClass()){
            return false;
        }
        Matrix matrix =(Matrix) o;
        if(n!=matrix.n || m!=matrix.m){
            return false;
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(main_matrix[i][j]!=matrix.main_matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    int hashcode(){
        double code=1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(main_matrix[i][j]!=0){
                    code=code*main_matrix[i][j];
                }
                else{
                    code=code*2;
                }
            }
        }
        return (int)code;
    }
    static int[][] create_identity(int n){
        //Метод, що створює одиничну матрицю
        int[][] matrix_id = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i!=j) {
                    matrix_id[i][j] = 0;
                }
                else{
                    matrix_id[i][j] = 0;
                }
            }
        }
        return matrix_id;
    }
    double[][] matr_for_minor(double[][] matrix, int n, int j1){
        //Функція що обчислює доповнювальний мінор для елемента матриці з індексами [0;j1]
        double[][] new_matr= new double[n-1][n-1] ;
        for(int i=1; i<n; i++){
            for(int j=0; j<n-1; j++){
                if( j>=j1){
                    new_matr[i-1][j]=matrix[i][j+1];
                }
                else{
                    if(j<j1){
                        new_matr[i-1][j]=matrix[i][j];
                    }
                }
            }
        }
        return new_matr;
    }
    double det(double matrix[][], int n){ //Функція, що обчислює визначник квадратної матриці n*n
        double det1=0;
        if(n==1){

            return matrix[0][0];
        }
        for(int i=0; i<n; i++){
            det1=det1+matrix[0][i]*((Math.pow(-1,i))*(det(matr_for_minor(matrix, n, i),n-1)));
        }
        return det1;
   }
   double alg_dop(double[][] matrix, int i1, int j1, int n){
        double[][] new_m=new double[n-1][n-1];
        for(int i=0; i<n-1; i++){
            for(int j=0; j<n-1; j++){
                if(i<i1){
                    if(j<j1){
                        new_m[i][j]=matrix[i][j];
                    }
                    else{
                        new_m[i][j]=matrix[i][j+1];
                    }
                }
                else{
                    if(j<j1){
                        new_m[i][j]=matrix[i+1][j];
                    }
                    else{
                        new_m[i][j]=matrix[i+1][j+1];
                    }
                }
            }
        }
        return Math.pow(-1, i1+j1)*det(new_m, n-1);
   }
   double[][] additional_matrix(double[][] matrix, int n){
        //Функція, що обчислює приєднану матрицю
        double[][] new_mat=new double[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                new_mat[j][i]=alg_dop(matrix, i, j, n);
            }
        }
        return new_mat;
   }
   public double[][] get_invertible_matrix(double[][] matrix, int n){   //Функція, що обчитслює обернену матрицю
        double[][] main_matrix_invert=new double[n][n];
        double d=det(matrix, n);
        if(d==0 || n!=m){
            System.out.println("Обернена матриця для даної не існує.");
        }
        else{
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    main_matrix_invert[j][i]=alg_dop(matrix, i, j, n)/(d);
                }
            }
       }
        return main_matrix_invert;
   }
}


