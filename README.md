# BonAreaIA

Equipo Miguel

Modelos entrenados
https://mega.nz/folder/hTwERRJT#s4WDDLKPfCmUuemCqhjNkg  

Poner trained_weights_final.h5 en Detector\DetectorEstanteria\Model_Weights  
Poner yolo.h5 en BonAreaIA\BonAreaIA\Detector\DetectorEstanteria\src\keras_yolo3  
yolov3.weights BonAreaIA\BonAreaIA\Detector\DetectorEstanteria\src\keras_yolo3  

## Prueba de la red neronal

Ir a BonAreaIA\Detector.  
Instalar dependencias (Requirements.txt)  
Ejecutar Servidor.py  

## Prueba integracion

1. Iniciar servidor Express
  cd Server
  node index.js
  
2. Iniciar emulador android
3. Iniciar la deteccion en tiempo real
  cd Detector\DetectorEstanteria
  python Servidor.py
