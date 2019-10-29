/**
 * @version V1.0
 * @Title: Test
 * @Package: PACKAGE_NAME
 * @author: Lenovo
 * @date: 2019/6/13 14:26
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class Test {

  public int add(int x, int y){
      int result = x;
      try {
            if (x<0){
            return 0;
            }
          result = x +y;
          return result;
      } finally {
          result = x -y;
      }
  }

    public static void main(String[] args) {
        Test t = new Test() ;

        System.out.println( t.add(3,5));
  }

}
