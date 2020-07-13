---
marp: true
---

# 数理最適化ハンズオン

---

## 数理最適化とは

制約の中でもっとも良い解を見つけるための手法

---

## 最適化問題

公共施設の配置、災害救助、最短路、待ち行列、コンテナ詰め込み、動的価格設定、プロジェクトスケジューリング、シフトスケジューリング、配送、在庫管理、エネルギー供給、生産計画、etc.

[「ORを探せ！」ポスター](http://www.orsj.or.jp/members/poster.pdf)

---

## 数理最適化に登場する概念
最適化問題は主に以下の５つの項目によって表現される

- 集合
- 変数
- 制約
- 目的関数
- パラメータ

---

## 習うより慣れろ

---

## 今日作るもの

シフトスケジューリング最適化問題

---

## ハンズオン準備

[Google Colaboratory 実行環境](https://colab.research.google.com/github/taichi0315/optimization-handson/blob/master/sample/model.ipynb)

---

## Google Colaboratory とは


- Googleが無料で提供する、対話型のPython環境
- Google Drive上で動作・管理する
- データ分析系ライブラリが一通り用意されている
- Jupyter Notebookという種類の形式

---

## Pulp とは

- Pythonの最適化ライブラリ
- 最適化ライブラリは何種類かのソルバーを扱う
- デフォルトで無料のソルバーを使うことができる

---

## Pulpのインストール

```bash
!pip install pulp
```

---

## Pulpのインポート

```python
from pulp import *
```

---

## 集合
モデルを構成する要素・物体（エンティティ）の集合
- 職員
- 日付

```python
# Set
Employee = { "emp1", "emp2", "emp3" }
Day      = { 1, 2, 3 }
```

---
## 変数
最適化をして求めたい値。最適解が求まると変数が最適値として定まる。
- 保育士$e$が日付$d$に出勤するかどうか（0-1変数）

```python
# Variable
x = {}
for emp in Employee:
    for day in Day:
        x[emp, day] = LpVariable(f"x[{emp}, {day}]", cat="Binary") 
```

---
## LpVariable
数理モデルの変数を表すクラス

```
x[emp, day] = LpVariable(name=f"x_{emp}_{day}", cat="Binary") 
```
#### name
変数の名前
#### cat
変数のカテゴリ

---

## 変数のカテゴリ
| 名前        | 説明    |
|------------|--------|
| Continuous | 連続変数 |
| Binary     | 0-1変数 |
| Integer    | 整数変数 |

Binary, Integerは計算が重いので、なるべくContinuous

---

## LpProblem
1つのモデルを管理するクラス

```python
model = LpProblem(sense=LpMinimize)
```


---

## 参考リンク
- [PuLPによるモデル作成方法 -Pythonオンライン学習サービス PyQ（パイキュー）ドキュメント](https://docs.pyq.jp/python/math_opt/pulp.html)
- [Pulp公式ドキュメント](https://coin-or.github.io/pulp/)

---