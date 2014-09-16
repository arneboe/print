from urllib.request import urlopen
import json
from PyQt4 import QtGui, QtCore
import sys
from time import sleep

def words():
    url = "http://www.pornmd.com/getliveterms"
    response = urlopen(url)

    data = json.loads(response.read().decode("UTF-8")) #is a list of dicts
    ret = [d["keyword"] for d in data]
    return ret


class WorkThread(QtCore.QThread):

    def __init__(self, label):
        QtCore.QThread.__init__(self)
        self.label = label
        self.i = 0

    def run(self):
        while True:
            ws = words()
            for w in ws:
                label.setText(w)
                sleep(5)

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    widget = QtGui.QWidget()
    widget.resize(250,250)
    widget.showFullScreen()
    label = QtGui.QLabel(widget)
    label.setFont(QtGui.QFont("Arial", 60))
    layout = QtGui.QHBoxLayout()
    layout.addWidget(label, 1, QtCore.Qt.AlignCenter)
    widget.setLayout(layout)
    t = WorkThread(label)
    t.start()
    sys.exit(app.exec_())

