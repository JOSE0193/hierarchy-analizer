import React from 'react';
import HierarchyBuilder from "@/components/HierarchyBuilder";
import styles from '@/app/page.module.scss'


const Home: React.FC = () => {
  return (
    <div className={styles.container}>
      <HierarchyBuilder />
    </div>
  );
};

export default Home;