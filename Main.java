public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix();
        Matrix m1 = new Matrix();
        double test_matrix1[][]={{1,0,0},{0,1,0},{0,0,1}};
        double test_matrix2[][]={{1,1,1},{1,1,1},{1,1,1}};
        double test_matrix3[][]={{1,0,0},{0,2,0},{0,0,3}};
        double test_matrix4[][]={{1,2,3},{4,1,6},{7,8,1}};
        m.create_fixed(3,3);

        m1.create_fixed(3,3);
        System.out.println("Заповніть матрицю m:");
        m.fill_matrix();
        System.out.println("Заповніть матрицю m1:");
        m1.fill_matrix();

        System.out.println("m.equals(m1)?\n"+m.equals(m1));
        System.out.println("m1.equals(m)?\n"+m1.equals(m));
        System.out.println("hashcode m: "+m.hashcode());
        System.out.println("hashcode m1: "+m1.hashcode());
        //m.show_matrix_2(m.matr_for_minor(m.main_matrix,3, 1),2,2);
        //System.out.println(m.det(test_matrix1, 3));
        //System.out.println(m.det(test_matrix2, 3));
        //System.out.println(m.det(test_matrix3, 3));
        //System.out.println(m.det(test_matrix4, 3));
        m.get_invertible_matrix(m.main_matrix, m.n);
        System.out.println("Обернена матриця для m:");
        m.show_matrix_2(m.get_invertible_matrix(m.main_matrix, m.n), m.n, m.n);
        System.out.println("Обернена матриця для m1:");
        m.show_matrix_2(m1.get_invertible_matrix(m1.main_matrix, m1.n), m1.n, m1.n);

    }

}
