
import styles from '../styles/components/SaveButton.module.scss';


interface SaveButtonProps {
  tree: any[];
}

const SaveButton: React.FC<SaveButtonProps> = ({ tree }) => {
  const downloadJson = () => {
    const jsonString = `data:text/json;charset=utf-8,${encodeURIComponent(
      JSON.stringify(tree)
    )}`;
    const link = document.createElement('a');
    link.href = jsonString;
    link.download = 'hierarchy.json';
    link.click();
  };

  return (
    <button className={styles.saveButton} onClick={downloadJson}>
      Salvar Hierarquia
    </button>
  );
};

export default SaveButton;