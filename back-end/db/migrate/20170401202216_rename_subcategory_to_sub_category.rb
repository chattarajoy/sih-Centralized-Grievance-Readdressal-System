class RenameSubcategoryToSubCategory < ActiveRecord::Migration[5.0]
  def change
    rename_column :complaints, :subcategory, :sub_category
  end
end
