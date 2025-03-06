# Android Oyun Projesi

## Proje Hakkında
Bu proje, modern Android geliştirme araçları ve mimarisi kullanılarak geliştirilmiş bir mobil oyun uygulamasıdır. 

## Özellikler
- Modern kullanıcı arayüzü
- Yüksek performanslı oyun motoru
- Çevrimiçi çok oyunculu destek
- Başarı sistemi
- Kişiselleştirilebilir karakterler

## Teknik Özellikler
- Programlama Dili: Kotlin
- Minimum SDK Sürümü: API 21 (Android 5.0 Lollipop)
- Hedef SDK Sürümü: API 33 (Android 13)
- Mimari: MVVM (Model-View-ViewModel)
- Veritabanı: Room
- Ağ İstekleri: Retrofit
- Asenkron İşlemler: Coroutines

## Kurulum
1. Bu projeyi klonlayın:
```
git clone https://github.com/kullaniciadi/androidoyun.git
```
2. Android Studio'da açın
3. Gradle senkronizasyonunu bekleyin
4. Uygulamayı bir emülatörde veya gerçek cihazda çalıştırın

## Proje Yapısı
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/ornek/androidoyun/
│   │   │   ├── data/         # Veri katmanı
│   │   │   ├── di/           # Bağımlılık enjeksiyonu
│   │   │   ├── domain/       # İş mantığı katmanı
│   │   │   ├── presentation/ # UI bileşenleri
│   │   │   ├── util/         # Yardımcı sınıflar
│   │   │   └── MainActivity.kt
│   │   │
│   │   └── res/             # Kaynaklar (layout, drawable, vb.)
│   │
│   └── test/                # Birim testleri
│
└── build.gradle            # Uygulama seviyesi yapılandırma
```

## Katkıda Bulunma
1. Bu depoyu forklayın
2. Yeni bir dal oluşturun (`git checkout -b yeni-ozellik`)
3. Değişikliklerinizi commit edin (`git commit -m 'Yeni özellik: Açıklama'`)
4. Dalınızı uzak depoya push edin (`git push origin yeni-ozellik`)
5. Bir Pull Request açın

## Lisans
Bu proje [MIT Lisansı](LICENSE) altında lisanslanmıştır.

## İletişim
Sorularınız için: ornek@email.com 