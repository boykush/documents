# optimization-handson
## シフトスケジューリング問題の定式化
### 集合
- $E:$ 職員の集合
- $D:$ 対象日の集合
### 変数
- $x_{ed}:$ 職員$e(\in E)$が日付$d(\in D)$に出勤するかどうかのバイナリ変数
- $xp_e:$ 職員$e(\in E)$が出勤超過ペナルティ日数$pd$を超過した日数
### 制約
- 職員の最低配置人数を満たす  
$\sum_{e \in E} x_{ed} >= lb \quad \forall d \in D$

### 出勤超過ペナルティ日数を定める
- $xp_e = \sum_{d \in D} x_{ed} - pd \quad \forall e \in E$

### パラメータ
- $lb:$ 職員の最低配置人数
- $pd:$ 出勤超過ペナルティ日数
- $pa:$ 出勤超過ペナルティ日数を超過した1日あたりのペナルティ量
### 目的関数
- 全職員の出勤超過ペナルティ日数を超過したペナルティ量の合計  
$\sum_{e \in E} xp_e * pa$