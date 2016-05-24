
package vya.odev.pkg1;

import java.util.*;


public class VYAOdev1 {

   
    public static void main(String[] args) {
        System.out.println("Ogrenci sayisini giriniz: "); //ilk ogrenci sayisini girmemiz gerekiyor
        Scanner kbd = new Scanner (System.in);
        
        int N = kbd.nextInt();
        
        float[][] ders_vize = new float[N][2];  //Kusuratli degerler olma ihtimaline binaen float olarak vize, final ve ortalamalari hesaplatiyoruz
        float[][] ders_final = new float[N][2];
        float[][] ders_ortalama = new float[N][2];
        
        int[][] ders_gecme_sayisi = new int[2][4]; //istatistiksel olarak derse katilan ogrenci sayisinda ka kisi dersi gectigini belirtiyoruz
        
        // Ders ortalamalarini hesaplandi ve kategori sayilari belirlendi(1 ve 1a Yapildi)
        for (int i = 0; i < N ; i++) {
            for (int ders = 0; ders < 2; ders++) {
                System.out.println((i + 1) + "-ci ogrencinin ders" + (ders + 1) + " icin vize ve final notlarini giriniz:");
            
                ders_vize[i][ders] = kbd.nextFloat();//Her ogrencinin vize ve final notlari okunuyor
                ders_final[i][ders] = kbd.nextFloat();
            
                ders_ortalama[i][ders] = (ders_vize[i][ders] + ders_final[i][ders]) / 2;//her ogrencinin vize ve final ortalamasi hesaplaniyor
                
                if (ders_ortalama[i][ders] >= 85){ //Notlarin kategorilenmesi yapiliyor
                    ders_gecme_sayisi[ders][0]++;
                } else
                if (ders_ortalama[i][ders] >= 65){
                    ders_gecme_sayisi[ders][1]++;
                } else
                if (ders_ortalama[i][ders] >= 50){
                    ders_gecme_sayisi[ders][2]++;
                } else {
                    ders_gecme_sayisi[ders][3]++;
                }
            }
        }
        //Kategori istatistikleri yazildi.
        for (int ders = 0; ders < 2; ders++) {
            System.out.println((ders + 1) + "-ci dersten 85-100 ortalamali ogrenciler sayisi: " + ders_gecme_sayisi[ders][0]);
            System.out.println((ders + 1) + "-ci dersten 65-84 ortalamali ogrenciler sayisi: " + ders_gecme_sayisi[ders][1]);
            System.out.println((ders + 1) + "-ci dersten 50-64 ortalamali ogrenciler sayisi: " + ders_gecme_sayisi[ders][2]);
            System.out.println((ders + 1) + "-ci dersten 0-49 ortalamali ogrenciler sayisi: " + ders_gecme_sayisi[ders][3]);
        }
        //Dersin Gecilip Gecilmedigi yazildi(1b)
        for (int i = 0; i < N; i++) {
            for (int ders = 0; ders < 2; ders++) {
                if (ders_ortalama[i][ders] > 50 && ders_final[i][ders] > 50) {
                    System.out.println((i + 1) + "-ci ogrenci ders " + (ders + 1) + "-i gecti.");
                } else {
                    System.out.println((i + 1) + "-ci ogrenci ders " + (ders + 1) + "-i gecmedi.");
                    if (ders_ortalama[i][ders] <= 50) {
                        System.out.println((i + 1) + "-ci ogrencinin ders " + (ders + 1) + " ortalam notu 50i gecmedi");
                    }   
                    if (ders_final[i][ders] <= 50) {
                        System.out.println((i + 1) + "-ci ogrencinin ders " + (ders + 1) + " final notu 50i gecmedi");
                    }
                    
                    System.out.println((i + 1) + "-ci ogrenci ders " + (ders + 1) + "-i gecmek icin final notu " + Math.max(50, 100 - ders_vize[i][ders]) + " den fazla olmali.");
                }
            }
        }
        
        QuickSort(ders_ortalama, 0, N- 1, 0); //Ders notlarinin siralamasi yapildi
        QuickSort(ders_ortalama, 0, N -1, 1);
        
        //Derslerin Siralanmis halleri yazildi.
        for (int ders = 0; ders < 2; ders++) {
            System.out.println((ders + 1) + "-ci ders ortalama not siralamasi:");
            for (int i = 0; i < N; i++) {
                System.out.print(ders_ortalama[i][ders] + " ");
                System.out.println();
            }
        }
    }
                //Algoritma dersinde anlatildigi gibi Quick sort yazilimi yapildi
    private static void QuickSort(float[][] a, int sol, int sag, int k) {
        int i = sol, j = sag;
        float v = a[sol + (sag -  sol) / 2][k];
        while ( i <= j ) {  
            while (a[i][k] < v) {
                i++;
            } 
            while (a[j][k] > v) {
                j--;
            }
            if (i <= j) {
                degis(a, i, j, k);
                i++;
                j--;
            }
        }
        if (sol < j)
            QuickSort(a, sol, i-1, k);
        if (i < sag)
            QuickSort(a, i+1, sag, k);
    }
//Iki notun Quick sort'a gore yerlerinin degisimini yapiyor.
    private static void degis(float[][] a, int i, int j, int k) {
        float bos; 
        bos = a[i][k];
        a[i][k] = a[j][k];
        a[j][k] = bos;
    }
}
