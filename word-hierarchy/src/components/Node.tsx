import React, { useState } from 'react';
import styles from '../styles/components/Node.module.scss';

interface NodeProps {
  node: any;
  indexPath: number[];
  currentPath: number[];
  onAddChild: (indexPath: number[]) => void;
  onUpdateName: (indexPath: number[], name: string) => void;
  onAddItems: (indexPath: number[], items: string) => void;
}

const Node: React.FC<NodeProps> = ({ node, indexPath, onAddChild, onUpdateName, onAddItems }) => {
  const [items, setItems] = useState(''); // Estado para capturar os itens

  return (
    <div className={styles.node}>
      <input 
        type="text" 
        className={styles.input}
        placeholder="Nome do nó" 
        value={node.name} 
        onChange={(e) => onUpdateName(indexPath, e.target.value)}
      />
      <button className={styles.button} onClick={() => onAddChild([...indexPath])}>Adicionar Subcategoria</button>
      <input
        type="text"
        className={styles.input}
        placeholder="Itens da Categoria (separados por vírgula)"
        value={items}
        onChange={(e) => setItems(e.target.value)}
      />
      <button className={styles.button} onClick={() => onAddItems(indexPath, items)}>Adicionar Itens</button>
      {node.children.length > 0 && (
        <div className={styles.children}>
          {node.children.map((child: any, index: number) => (
            <Node 
              key={index} 
              node={child} 
              indexPath={[...indexPath, index]}
              onAddChild={onAddChild} 
              onUpdateName={onUpdateName} 
              onAddItems={onAddItems}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default Node;
