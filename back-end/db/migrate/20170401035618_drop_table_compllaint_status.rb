class DropTableCompllaintStatus < ActiveRecord::Migration[5.0]
  def change
  	drop_table :complaint_statuses
  end
end
