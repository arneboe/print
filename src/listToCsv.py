import camelot
import cv2
import ghostscript
import tabula

file = "/home/arne/Downloads/ListofDeathsActual.pdf"
tabula.convert_into(file, "../TheList_2021.csv", output_format="tsv", pages="all")
#tables = tabula.read_pdf(file, pages="all")
#print("len " + int(len(tables)))
#print(tables[0])