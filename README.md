# Cryptarithmetic

## Deskripsi Singkat
Program Cryptarithmetic mencari solusi dari persoalan *Cryptarithmetic* dengan algoritma *Brute Force*. *Cryptarithmetic* adalah sebuah *puzzle* penjumlahan di Matematika di mana angka diganti menjadi huruf. Setiap angkanya direpresentasikan dengan huruf yang berbeda dengan huruf pertama tidak menyatakan angka 0, terdiri atas dua atau lebih *operand*, dan jumlah huruf unik paling banyak 10 buah.

## Contoh
![Contoh1](./img/SendMoreMoney.png)
![Contoh2](./img/ThreeThreeTwoTwoOneEleven.png)
![Contoh3](./img/CoolMathBlogTeach.png)
![Contoh4](./img/SunFunSwim.png)
[Contoh Lainnya Cek Disini](./img)

## Requirement Program
- Java (Minimum Java 8)

## Cara Menggunakan Program
### Cara Manual (Windows/Linux/MacOS)
1. Buka terminal pada folder ini
2. Masuk ke folder src
3. Compile dengan menggunakan command
```sh
javac -d ../bin ./cryptarithms/*.java
```
4. Masuk ke folder bin
5. Jalankan program dengan menggunakan command
```sh
java cryptarithms.CryptaBF
```
6. Tunggu kurang lebih 10 detik untuk program menyiapkan permutasi
7. Ketika selesai, program akan meminta nama file yang berisikan problema soal (sertakan ekstensi file seperti .txt)
### Cara Otomatis (Windows)
1. Jalankan run.bat dan langkah 1-6 pada tata cara manual akan dilakukan secara otomatis
2. Tunggu kurang lebih 10 detik untuk program menyiapkan permutasi
3. Ketika selesai, program akan meminta nama file yang berisikan problema soal (sertakan ekstensi file seperti .txt)

## Identitas Pembuat
Program ini dibuat oleh [Steven Nataniel](https://github.com/ravielze) untuk menuntaskan tugas kecil 1 dari mata kuliah IF2211 Strategi Algoritma
